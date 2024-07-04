package com.Lubee.Lubee.user.repository;

import com.Lubee.Lubee.common.enumSet.LoginType;
import com.Lubee.Lubee.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndLoginType(String username, LoginType loginType);


}