package com.Lubee.Lubee.user_calendar_memory.repository;

import com.Lubee.Lubee.calendar_memory.domain.CalendarMemory;
import com.Lubee.Lubee.user.domain.User;
import com.Lubee.Lubee.user_calendar_memory.domain.UserCalendarMemory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UserCalendarMemoryRepository extends JpaRepository<UserCalendarMemory, Long> {

    @Query("SELECT ucm FROM UserCalendarMemory ucm " +
            "WHERE ucm.user = :user " +
            "AND ucm.calendarMemory.calendar.eventDate = :today_date")
    List<UserCalendarMemory> findByUserAndMemoryDate(@Param("user") User user, @Param("today_date") Date todayDate);
}