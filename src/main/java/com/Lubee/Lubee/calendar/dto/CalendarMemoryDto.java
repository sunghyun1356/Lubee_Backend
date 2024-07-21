package com.Lubee.Lubee.calendar.dto;

import com.Lubee.Lubee.memory.dto.MemoryBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarMemoryDto {
    int year;
    int month;
    MemoryBaseDto memoryBaseDto;

    public static CalendarMemoryDto of(int year, int month, MemoryBaseDto memoryBaseDto) {
        return new CalendarMemoryDto(year, month, memoryBaseDto);
    }

}
