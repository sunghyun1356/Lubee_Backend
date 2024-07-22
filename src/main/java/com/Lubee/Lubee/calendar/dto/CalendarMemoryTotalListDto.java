package com.Lubee.Lubee.calendar.dto;

import com.Lubee.Lubee.memory.dto.MemoryBaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CalendarMemoryTotalListDto {
    // 모든 데이터를 다 담고 있다 -> 전체 데이터
    private List<CalendarMemoryYearMonthDto> calendarMemoryYearMonthDtoList;

    public static CalendarMemoryTotalListDto of(List<CalendarMemoryYearMonthDto> calendarMemoryYearMonthDtoList) {
        CalendarMemoryTotalListDto instance = new CalendarMemoryTotalListDto();
        instance.setCalendarMemoryYearMonthDtoList(calendarMemoryYearMonthDtoList);
        return instance;
    }
}
