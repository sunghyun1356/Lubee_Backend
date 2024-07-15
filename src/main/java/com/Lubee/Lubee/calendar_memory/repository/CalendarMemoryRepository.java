package com.Lubee.Lubee.calendar_memory.repository;

import com.Lubee.Lubee.calendar_memory.domain.CalendarMemory;
import com.Lubee.Lubee.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalendarMemoryRepository extends JpaRepository<CalendarMemory, Long> {
    // calendarmemory의 memory에서 date의 값 yyyy-MM-dd에서 yyyy가 같고 MM 가 맞는 것을 찾아준다. 그리고 user가 같은 것을 찾는다 .
    @Query()
    List<CalendarMemory> findAllByCalendarYearAndCalendarMonth(@Param("user") User user, @Param("year") Integer yaer, @Param("month")Integer month);
}
