package com.Lubee.Lubee.memory.service;

import com.Lubee.Lubee.calendar.domain.Calendar;
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
import com.Lubee.Lubee.location.service.LocationService;
import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.memory.dto.MemoryBaseDto;
import com.Lubee.Lubee.memory.dto.MemoryCreateRequestDto;
import com.Lubee.Lubee.memory.repository.MemoryRepository;
import com.Lubee.Lubee.user.domain.User;
import com.Lubee.Lubee.user.repository.UserRepository;
import com.Lubee.Lubee.user_calendar_memory.domain.UserCalendarMemory;
import com.Lubee.Lubee.user_calendar_memory.repository.UserCalendarMemoryRepository;
import com.Lubee.Lubee.user_memory.domain.UserMemory;
import com.Lubee.Lubee.user_memory.repository.UserMemoryRepository;
import com.Lubee.Lubee.user_memory_reaction.domain.UserMemoryReaction;
import com.Lubee.Lubee.user_memory_reaction.repository.UserMemoryReactionRepository;
import com.Lubee.Lubee.user_memory_reaction.service.UserMemoryReactionService;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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
    private final UserMemoryReactionRepository userMemoryReactionRepository;
    private final AmazonS3Client amazonS3Client;
    private final UserRepository userRepository;
    private final UserMemoryRepository userMemoryRepository;
    private final CoupleRepository coupleRepository;
    private final CalendarRepository calendarRepository;
    private final CalendarMemoryRepository calendarMemoryRepository;
    private final UserCalendarMemoryRepository userCalendarMemoryRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

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
    public List<MemoryBaseDto> getMemoryBase(List<UserCalendarMemory> userCalendarMemories) {
        ArrayList<MemoryBaseDto> memoryBaseDtoArrayList = new ArrayList<>();

        for (UserCalendarMemory userCalendarMemory : userCalendarMemories) {
            Memory memory = userCalendarMemory.getCalendarMemory().getMemory();
            String location_name = locationService.getLocationByMemory(memory).getName();
            String picture = locationService.getLocationByMemory(memory).getPicture();
            List<UserMemoryReaction> userMemoryReactionList = userMemoryReactionService.getUserMemoryReactionsByMemory(memory);

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

            MemoryBaseDto memoryBaseDto = MemoryBaseDto.of(memory.getMemory_id(), userCalendarMemory.getUser().getId(), location_name, picture, profile, reaction1, reaction2);
            memoryBaseDtoArrayList.add(memoryBaseDto);
        }

        return memoryBaseDtoArrayList;
    }

    @Transactional
    public void createMemory(UserDetails loginUser, MemoryCreateRequestDto memoryRequest) {
        // 사용자 정보 가져오기
        User user = userRepository.findByUsername(loginUser.getUsername()).orElseThrow(
                () -> new RestApiException(ErrorType.NOT_FOUND_USER)
        );
        Couple couple = coupleRepository.findCoupleByUser(user).orElseThrow(
                () -> new RestApiException(ErrorType.NOT_FOUND_COUPLE)
        );

        try {
            // 파일 업로드 처리
            String fileName = memoryRequest.getPicture().getOriginalFilename();
            String folder = "/pictures"; // 저장할 폴더
            String fileUrl = "https://" + bucket + ".s3." + region + ".amazonaws.com" + folder + "/" + fileName;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(memoryRequest.getPicture().getContentType());
            metadata.setContentLength(memoryRequest.getPicture().getSize());
            amazonS3Client.putObject(bucket + folder, fileName, memoryRequest.getPicture().getInputStream(), metadata);

            // 메모리 객체 생성 및 저장
            Memory memory = new Memory();
            memory.setContent(memoryRequest.getContent());
            memory.setLocationName(memoryRequest.getLocationName());
            memory.setTime(memoryRequest.getTime());
            memory.setPicture(fileUrl);
            memory.setCouple(couple);

            memory = memoryRepository.save(memory);

            // Calendar 존재 확인 및 생성
            Date eventDate = memoryRequest.getTime(); // Memory의 시간을 이벤트 날짜로 사용
            Calendar calendar = calendarRepository.findByCoupleAndEventDate(couple, eventDate);
            if (calendar == null)
            {
                calendar = Calendar.builder()
                        .couple(couple)
                        .eventDate(eventDate)
                        .build();
                calendar = calendarRepository.save(calendar);
            }

            // CalendarMemory 생성 및 저장
            CalendarMemory calendarMemory = CalendarMemory.builder()
                    .calendar(calendar)
                    .memory(memory)
                    .build();
            calendarMemoryRepository.save(calendarMemory);

            // UserMemory 생성 및 저장
            UserMemory userMemory = UserMemory.of(user, memory);
            userMemoryRepository.save(userMemory);

            // UserCalendarMemory 생성 및 저장
            UserCalendarMemory userCalendarMemory = UserCalendarMemory.of(user, calendarMemory);
            userCalendarMemoryRepository.save(userCalendarMemory);

            couple.setTotal_honey(couple.getTotal_honey()+1);
            coupleRepository.save(couple);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RestApiException(ErrorType.FILE_UPLOAD_FAILED);
        }
    }
    public MemoryBaseDto getOneMemory(UserDetails loginUser, Long memoryId) {
        User user = userRepository.findByUsername(loginUser.getUsername()).orElseThrow(
                () -> new RestApiException(ErrorType.NOT_FOUND)
        );
        Memory memory = memoryRepository.findById(memoryId).orElseThrow(
                () -> new RestApiException(ErrorType.NOT_FOUND)
        );

        // 해당 Memory에 대한 Reactions 가져오기 (최대 2개만)
        List<UserMemoryReaction> reactions = userMemoryReactionRepository.getUserMemoryReactionByMemory(memory);
        Reaction reaction1 = null;
        Reaction reaction2 = null;

        if (!reactions.isEmpty()) {
            reaction1 = reactions.get(0).getReaction();
            if (reactions.size() > 1) {
                reaction2 = reactions.get(1).getReaction();
            }
        }

        // MemoryBaseDto 생성
        Profile userProfile = user.getProfile();
        return MemoryBaseDto.of(
                memory.getMemory_id(),
                user.getId(),
                memory.getLocationName(),
                memory.getPicture(),
                userProfile,// 로그인 유저의 프로필 정보를 가져와서 설정
                reaction1,
                reaction2
        );
    }
    public List<Memory> getMemorybyUserAndDate(Date date, Couple couple) {
        return memoryRepository.findByMemoryAndUser(date, couple);
    }



}
