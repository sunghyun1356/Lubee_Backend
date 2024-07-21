package com.Lubee.Lubee.location.repository;

import com.Lubee.Lubee.location.domain.Location;
import com.Lubee.Lubee.memory.domain.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l JOIN Memory m On m.location = l")
    Optional<Location> findByMemories(Memory memory);
}
