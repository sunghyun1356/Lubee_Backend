package com.Lubee.Lubee.calendar.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CalendarMemoryListDto {
    private List<CalendarMemoryDto> calendarMemoryDtoList;

    public CalendarMemoryListDto(List<CalendarMemoryDto> calendarMemoryDtoList) {
        this.calendarMemoryDtoList = calendarMemoryDtoList;
    }

    public static CalendarMemoryListDto of(List<CalendarMemoryDto> calendarMemoryDtoList) {
        return new CalendarMemoryListDto(calendarMemoryDtoList);
    }

}
