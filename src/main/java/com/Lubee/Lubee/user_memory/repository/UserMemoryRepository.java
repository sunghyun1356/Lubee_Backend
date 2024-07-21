package com.Lubee.Lubee.user_memory.repository;

import com.Lubee.Lubee.user_memory.domain.UserMemory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMemoryRepository extends JpaRepository<UserMemory, Long> {
}
