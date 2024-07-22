package com.Lubee.Lubee.anniversary.service;

import com.Lubee.Lubee.anniversary.domain.Anniversary;
import com.Lubee.Lubee.anniversary.dto.AnniversaryDto;
import com.Lubee.Lubee.anniversary.dto.AnniversaryListDto;
import com.Lubee.Lubee.anniversary.repository.AnniversaryRepository;
import com.Lubee.Lubee.couple.domain.Couple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AnniversaryService {

    private final AnniversaryRepository anniversaryRepository;
    public AnniversaryListDto getAnniversaryInfo(Couple couple) {
        // 커플의 기념일 목록을 가져옴
        List<Anniversary> anniversaryList = anniversaryRepository.findAnniversariesByCouple(couple);

        // Anniversary 객체 리스트를 AnniversaryDto 객체 리스트로 변환
        List<AnniversaryDto> anniversaryDtoList = anniversaryList.stream()
                .map(anniversary -> AnniversaryDto.of(anniversary.getTitle(), anniversary.getAnniversary_date()))
                .collect(Collectors.toList());

        // AnniversaryDto 리스트를 AnniversaryListDto로 반환
        return AnniversaryListDto.of(anniversaryDtoList);
    }
}
