package com.Lubee.Lubee.user.repository;

import com.Lubee.Lubee.common.enumSet.LoginType;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.user.domain.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndLoginType(String username, LoginType loginType);

    @Query("SELECT u FROM User u JOIN u.couple c WHERE c = :couple AND u != :user")
    Optional<User> findRestUser(@Param("user") User user, @Param("couple") Couple couple);
}