package com.Lubee.Lubee.calendar_memory.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Getter
public class CalendarMemoryIdDto {
    Long id;
    // day에 해당하는 값
    int day;
}
