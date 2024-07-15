package com.Lubee.Lubee.memory.service;

import com.Lubee.Lubee.calendar.domain.Calendar;
import com.Lubee.Lubee.common.enumSet.ErrorType;
import com.Lubee.Lubee.common.exception.RestApiException;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.enumset.Profile;
import com.Lubee.Lubee.enumset.Reaction;
import com.Lubee.Lubee.location.service.LocationService;
import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.memory.dto.HomeDto;
import com.Lubee.Lubee.memory.dto.MemoryBaseDto;
import com.Lubee.Lubee.memory.repository.MemoryRepository;
import com.Lubee.Lubee.user.domain.User;
import com.Lubee.Lubee.user_calendar_memory.domain.UserCalendarMemory;
import com.Lubee.Lubee.user_memory_reaction.domain.UserMemoryReaction;
import com.Lubee.Lubee.user_memory_reaction.service.UserMemoryReactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoryService {

    private final LocationService locationService;
    private final MemoryRepository memoryRepository;
    private final UserMemoryReactionService userMemoryReactionService;

    public String getToday()
    {
        Date today = new Date();

        // 날짜 포맷 정의하기
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-DD-MM");

        // 포맷에 맞춰 날짜를 문자열로 변환하기
        return formatter.format(today);
    }

    public Long getLoveDays(Date startDate) {
        // Date를 LocalDate로 변환
        LocalDate specificDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // 오늘 날짜 가져오기
        LocalDate today = LocalDate.now();

        // 날짜 차이 계산하기

        return ChronoUnit.DAYS.between(specificDate, today);
    }
    public List<MemoryBaseDto> getMemoryBase(UserCalendarMemory userCalendarMemory)
    {
        ArrayList<MemoryBaseDto> memoryBaseDtoArrayList= new ArrayList<MemoryBaseDto>();
        for (UserCalendarMemory userCalendarMemory : UserCalendarMemory):
        {
            Memory memory = userCalendarMemory.getCalendarMemory().getMemory();
            String location_name = locationService.getLocationByMemory(memory).getName();
            String picture = locationService.getLocationByMemory(memory).getPicture();
            List<UserMemoryReaction> userMemoryReactionList = userMemoryReactionService.getUserMemoryReactionsByMemory(memory);
            int i = 0;
            Reaction reaction1 = null;
            Reaction reaction2 =null;
            Profile profile = null;
            for (UserMemoryReaction userMemoryReaction : userMemoryReactionList)
            {
                reaction1 = userMemoryReaction.getReaction();
                i++;
                if (i==1)
                {
                    reaction2 = userMemoryReaction.getReaction();
                    profile = userMemoryReaction.getUser().getProfile();
                }

            }
            memory = MemoryBaseDto.of(location_name, picture, profile, reaction1, reaction2);
            memoryBaseDtoArrayList.add(memory);
        }

    }

    public Memory getMemorybyUserAndDate(Date date, Couple couple)
    {
        return memoryRepository.findByMemoryAndUser(date, couple).orElseThrow(
                () -> new RestApiException(ErrorType.NOT_FOUND_USER)
        );
    }


}
