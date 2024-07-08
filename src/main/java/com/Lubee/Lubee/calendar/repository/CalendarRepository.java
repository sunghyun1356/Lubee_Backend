package com.Lubee.Lubee.calendar.repository;

import com.Lubee.Lubee.calendar.domain.Calendar;
import com.Lubee.Lubee.couple.domain.Couple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    Calendar findByCoupleAndEventDate(Couple couple, Date eventDate);   // 해당 날짜에 생성된 커플달력이 있는지

    // 커플이 해당 년/월에 생성한 Calendar를 조회
    @Query("SELECT c FROM Calendar c WHERE c.couple = :couple AND FUNCTION('YEAR', c.eventDate) = :year AND FUNCTION('MONTH', c.eventDate) = :month")
    List<Calendar> findAllByCoupleAndYearAndMonth(@Param("couple") Couple couple, @Param("year") int year, @Param("month") int month);
}
