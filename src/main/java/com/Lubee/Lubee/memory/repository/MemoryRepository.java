package com.Lubee.Lubee.memory.repository;

import com.Lubee.Lubee.calendar.domain.Calendar;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    @Query("SELECT m FROM Memory m WHERE m.time =:date and m.couple =:couple ")
    List<Memory> findByMemoryAndUser(Date date, Couple couple);

    @Query("SELECT m FROM Memory m WHERE m.couple =:couple AND FUNCTION('YEAR', m.time) = :year AND FUNCTION('MONTH', m.time) = :month AND FUNCTION('DAY', m.time) = :day")
    List<Memory> findAllByCoupleAndYearAndMonthAndDay(@Param("couple") Couple couple, @Param("year") int year, @Param("month") int month, @Param("day") int day);

}
