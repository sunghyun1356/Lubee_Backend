package com.Lubee.Lubee.calendar_memory.service;

import com.Lubee.Lubee.calendar.repository.CalendarRepository;
import com.Lubee.Lubee.calendar_memory.domain.CalendarMemory;
import com.Lubee.Lubee.calendar_memory.dto.CalendarMemoryListDto;
import com.Lubee.Lubee.calendar_memory.repository.CalendarMemoryRepository;
import com.Lubee.Lubee.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CalendarMemoryService {

    private final CalendarRepository calendarRepository;
    private final CalendarMemoryRepository calendarMemoryRepository;

//    public CalendarMemoryListDto getYearlyMonthlyCalenderInfo(User user, Integer year, Integer month)
//    {
//        // year와 month가 일치하는 CalendarMemory를 찾아서 id와 그것의 day의 값을 전달해준다
//        List<CalendarMemory> calendarMemoryList = calendarMemoryRepository.findAllByCalendarYearAndCalendarMonth(user, year, month);
//        // calendarMemoryList 를 돌면서 calenderMemoryRepository의 값과 그 값에 해당하는 객체의 memory의 date의 dd값을 day에 저장하여 dto로 만들어 준다.
//
//
//        return calendarMemoryList;
//    }
}
