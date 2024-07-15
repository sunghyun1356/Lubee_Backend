package com.Lubee.Lubee.user_calendar_memory.repository;

import com.Lubee.Lubee.calendar_memory.domain.CalendarMemory;
import com.Lubee.Lubee.user.domain.User;
import com.Lubee.Lubee.user_calendar_memory.domain.UserCalendarMemory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCalendarMemoryRepository extends JpaRepository<UserCalendarMemory, Long> {
    UserCalendarMemory getUserCalendarMemoryByUserAndCalendarMemory(User user, CalendarMemory calendarMemory);
}
