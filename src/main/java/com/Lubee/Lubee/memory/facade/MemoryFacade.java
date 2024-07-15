package com.Lubee.Lubee.memory.facade;

import com.Lubee.Lubee.calendar.service.CalendarService;
import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.common.api.ResponseUtils;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.couple.service.CoupleService;
import com.Lubee.Lubee.date_comment.dto.DateCommentBaseDto;
import com.Lubee.Lubee.date_comment.service.DateCommentService;
import com.Lubee.Lubee.enumset.Profile;
import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.memory.dto.HomeDto;
import com.Lubee.Lubee.memory.dto.MemoryBaseDto;
import com.Lubee.Lubee.memory.service.MemoryService;
import com.Lubee.Lubee.user.domain.User;
import com.Lubee.Lubee.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoryFacade {

    private final MemoryService memoryService;
    private final CalendarService calendarService;
    private final UserService userService;
    private final CoupleService coupleService;
    private final DateCommentService dateCommentService;

    @Transactional
    public ApiResponseDto<HomeDto> getHomeInfo(UserDetails loginUser, Date time)
    {
        // 유저정보로 커플 정보 얻기
        // 유저 정보로 memory 얻기 ->
        // 커플 정보로 데이트 코멘트 조회
        // 커플 정보로 사귄날짜 보여주기
        // 오늘 날짜 보여주기
        // 커플 정보로 프로필 정보 보여주기
        // 커플 정보로 꿀 개수 가져오기
        // memory에서 정보 가져오기
        // date_comment에서 가져오기
        User user = userService.getUser(loginUser);
        Couple couple = coupleService.getCoupleByUser(user);
        Memory memory = memoryService.getMemorybyUserAndDate(time, couple);
        String today_date = memoryService.getToday();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        today_date = formatter.parse(today_date);
        long loveDays = memoryService.getLoveDays(couple.getStartDate());
        int honey = (int) (calendarService.getToalHoney(user)/5);
        List<Profile> profileList = coupleService.getCouplesProfile(couple);
        List<DateCommentBaseDto> dateCommentBaseDtos = dateCommentService.getDateCommentsByCoupleAndCalendar(couple, calendar)
        List<MemoryBaseDto> memoryBaseDtoList = memoryService.getMemoryBase(memory);
        HomeDto homeDto = new HomeDto(today_date,loveDays, honey, profileList, dateCommentBaseDtos, memoryBaseDtoList);

        return ResponseUtils.ok(homeDto, null);
    }
}
