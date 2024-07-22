package com.Lubee.Lubee.calendar.dto;

import com.Lubee.Lubee.memory.dto.MemoryBaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CalendarMemoryDayDto {
    // 오늘 날짜에 대한 dto를 전달해 주는 것
    private int day;
    private List<MemoryBaseDto> memoryBaseListDto;

    public static CalendarMemoryDayDto of(int day, List<MemoryBaseDto> memoryBaseListDto) {
        CalendarMemoryDayDto instance = new CalendarMemoryDayDto();
        instance.setDay(day);
        instance.setMemoryBaseListDto(memoryBaseListDto);
        return instance;
    }

}
