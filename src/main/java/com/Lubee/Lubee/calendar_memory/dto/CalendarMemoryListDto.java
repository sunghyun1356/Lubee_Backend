package com.Lubee.Lubee.calendar_memory.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
@Getter
public class CalendarMemoryListDto {
    List<CalendarMemoryIdDto> calendarMemoryIdDtoList;
}
