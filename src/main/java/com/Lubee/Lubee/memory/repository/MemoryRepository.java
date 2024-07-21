package com.Lubee.Lubee.memory.repository;

import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    @Query("SELECT m FROM Memory m WHERE m.time =:date and m.couple =:couple ")
    Optional<Memory> findByMemoryAndUser(Date date, Couple couple);

}
