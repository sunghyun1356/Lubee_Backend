package com.Lubee.Lubee.location.repository;

import com.Lubee.Lubee.location.domain.Location;
import com.Lubee.Lubee.memory.domain.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    // 검색명을 입력하면 List 반환
    List<Location> findByNameContaining(String name);

    @Query("SELECT l FROM Location l JOIN Memory m On m.location = l")
    Optional<Location> findByMemories(Memory memory);
}
