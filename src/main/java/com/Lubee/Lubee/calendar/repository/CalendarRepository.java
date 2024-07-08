package com.Lubee.Lubee.calendar.repository;

import com.Lubee.Lubee.calendar.domain.Calendar;
import com.Lubee.Lubee.couple.domain.Couple;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    Calendar findByCoupleAndEventDate(Couple couple, Date eventDate);   // 해당 날짜에 생성된 커플달력이 있는지
}
