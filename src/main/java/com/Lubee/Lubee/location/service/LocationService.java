package com.Lubee.Lubee.location.service;

import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.common.api.ErrorResponse;
import com.Lubee.Lubee.common.api.ResponseUtils;
import com.Lubee.Lubee.common.enumSet.ErrorType;
import com.Lubee.Lubee.common.exception.RestApiException;
import com.Lubee.Lubee.location.domain.Location;
import com.Lubee.Lubee.location.dto.LocationDto;
import com.Lubee.Lubee.location.dto.LocationSearchResponse;
import com.Lubee.Lubee.location.repository.LocationRepository;
import com.Lubee.Lubee.memory.domain.Memory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    /**
     * 검색명을 중심으로 장소 검색
     * @param keyword       - 검색명
     * @return ApiResponseDto<LocationSearchResponse>
     */
    @Transactional(readOnly = true)
    public ApiResponseDto<LocationSearchResponse> findByKeyword(String keyword) {

        // 검색 결과 가져오기 -> 정확한 필터링 -> DTO 변환
        List<LocationDto> locationDtos =
                locationRepository.findByNameContaining(keyword).stream()
                .filter(location -> isRelevant(location.getName(), keyword))
                .map(LocationDto::from)
                .collect(Collectors.toList());

        if(locationDtos.isEmpty()){
            throw new RestApiException(ErrorType.LOCATION_NOT_FOUND);
        }

        return ResponseUtils.ok(new LocationSearchResponse(locationDtos), ErrorResponse.builder().status(200).message("위치 검색 성공").build());
    }

    // 장소명에 검색어가 정확히 들어가 있는지 검사하는 메서드
    private boolean isRelevant(String name, String keyword) {

        return name.contains(keyword);      // 정확히 검색명을 가지고 있는 검색어만 추출
    }

    public Location getLocationByMemory(Memory memory)
    {
        return locationRepository.findByMemories(memory).orElseThrow(
                () -> new RestApiException(ErrorType.NOT_FOUND_LOCATION)
        );
    }
}
