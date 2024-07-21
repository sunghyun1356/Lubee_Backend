package com.Lubee.Lubee.calendar_memory.service;

import com.Lubee.Lubee.calendar.dto.CalendarMemoryDto;
import com.Lubee.Lubee.calendar.dto.CalendarMemoryListDto;
import com.Lubee.Lubee.calendar.repository.CalendarRepository;
import com.Lubee.Lubee.calendar_memory.domain.CalendarMemory;
import com.Lubee.Lubee.calendar_memory.repository.CalendarMemoryRepository;
import com.Lubee.Lubee.common.enumSet.ErrorType;
import com.Lubee.Lubee.common.exception.RestApiException;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.couple.repository.CoupleRepository;
import com.Lubee.Lubee.enumset.Profile;
import com.Lubee.Lubee.enumset.Reaction;
import com.Lubee.Lubee.location.domain.Location;
import com.Lubee.Lubee.location.repository.LocationRepository;
import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.memory.dto.MemoryBaseDto;
import com.Lubee.Lubee.user.domain.User;
import com.Lubee.Lubee.user.repository.UserRepository;
import com.Lubee.Lubee.user_calendar_memory.domain.UserCalendarMemory;
import com.Lubee.Lubee.user_calendar_memory.repository.UserCalendarMemoryRepository;
import com.Lubee.Lubee.user_memory_reaction.domain.UserMemoryReaction;
import com.Lubee.Lubee.user_memory_reaction.repository.UserMemoryReactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CalendarMemoryService {

    private final CalendarRepository calendarRepository;
    private final CalendarMemoryRepository calendarMemoryRepository;
    private final CoupleRepository coupleRepository;
    private final UserRepository userRepository;
    private final UserMemoryReactionRepository userMemoryReactionRepository;
    private final UserCalendarMemoryRepository userCalendarMemoryRepository;
    private final LocationRepository locationRepository;
    public CalendarMemoryListDto getYearlyMonthlyCalenderInfo(UserDetails loginUser) {
        User user = userRepository.findByUsername(loginUser.getUsername()).orElseThrow(
                () -> new RestApiException(ErrorType.NOT_FOUND_USER)
        );
        Couple couple = coupleRepository.findCoupleByUser(user).orElseThrow(
                () -> new RestApiException(ErrorType.NOT_FOUND_COUPLE)
        );

        Date startDate = couple.getStartDate();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        int startYear = startCalendar.get(Calendar.YEAR);
        int startMonth = startCalendar.get(Calendar.MONTH);

        Calendar todayCalendar = Calendar.getInstance();
        int todayYear = todayCalendar.get(Calendar.YEAR);
        int todayMonth = todayCalendar.get(Calendar.MONTH);

        List<CalendarMemoryDto> calendarMemoryDtoList = new ArrayList<>();

        for (int year = startYear; year <= todayYear; year++) {
            int start = (year == startYear) ? startMonth : 0;
            int end = (year == todayYear) ? todayMonth : 11;

            for (int month = start; month <= end; month++) {
                // 특정 년도와 월에 해당하는 값들을 만족하는 calendarmemory 리스트를 가져온다
                List<CalendarMemory> calendarMemoryList = calendarMemoryRepository.findAllByCoupleAndYearAndMonth(couple, year, month);
                // 그 calendarmemory를 돌면서 각각에 대한 값들을 만들어준다
                int finalYear = year;
                int finalMonth = month;
                List<CalendarMemoryDto> monthlyMemoryDtoList = calendarMemoryList.stream().map(calendarMemory -> {
                    // 그 날짜의
                    Memory memory = calendarMemory.getMemory();
                    List<UserMemoryReaction> userMemoryReactionList = userMemoryReactionRepository.getUserMemoryReactionByUserAndMemory(user, memory);
                    int i = 0;
                    Reaction reaction1 = null;
                    Reaction reaction2 = null;
                    Profile profile = null;

                    for (UserMemoryReaction userMemoryReaction : userMemoryReactionList) {
                        if (i == 0) {
                            reaction1 = userMemoryReaction.getReaction();
                            i++;
                        } else if (i == 1) {
                            reaction2 = userMemoryReaction.getReaction();
                            profile = userMemoryReaction.getUser().getProfile();
                            i++;
                        }
                    }
                    Location location = locationRepository.findById(memory.getLocation().getLocation_id()).orElseThrow(
                            () -> new RestApiException(ErrorType.NOT_FOUND_LOCATION)
                    );
                    String locationName = location.getName();
                    MemoryBaseDto memoryBaseDto = MemoryBaseDto.of(
                            memory.getMemory_id(),
                            memory.getUserMemory().getUser().getId(),
                            locationName,
                            memory.getPicture(),
                            memory.getUserMemory().getUser().getProfile(),
                            reaction1,
                            reaction2
                    );
                    return CalendarMemoryDto.of(finalYear, finalMonth, memoryBaseDto);
                }).toList();

                calendarMemoryDtoList.addAll(monthlyMemoryDtoList);
            }
        }

        return CalendarMemoryListDto.of(calendarMemoryDtoList);
    }
}
