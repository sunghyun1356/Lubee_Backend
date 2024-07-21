package com.Lubee.Lubee.calendar_memory.repository;

import com.Lubee.Lubee.calendar_memory.domain.CalendarMemory;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalendarMemoryRepository extends JpaRepository<CalendarMemory, Long> {
    @Query("SELECT cm FROM CalendarMemory cm JOIN cm.memory m WHERE cm.calendar.couple = :couple AND YEAR(m.time) = :year AND MONTH(m.time) = :month")
    List<CalendarMemory> findAllByCoupleAndYearAndMonth(@Param("couple") Couple couple, @Param("year") int year, @Param("month") int month);
}
