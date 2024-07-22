package com.Lubee.Lubee.calendar.dto;

import com.Lubee.Lubee.memory.dto.MemoryBaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CalendarMemoryYearMonthDto {
    private int year;
    private int month;
    private List<CalendarMemoryDayDto> calendarMemoryDayDtoList;

    public static CalendarMemoryYearMonthDto of(int year, int month, List<CalendarMemoryDayDto> calendarMemoryDayDtoList) {
        CalendarMemoryYearMonthDto instance = new CalendarMemoryYearMonthDto();
        instance.setYear(year);
        instance.setMonth(month);
        instance.setCalendarMemoryDayDtoList(calendarMemoryDayDtoList);
        return instance;
    }

}
