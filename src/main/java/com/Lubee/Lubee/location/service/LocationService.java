package com.Lubee.Lubee.location.service;

import com.Lubee.Lubee.common.enumSet.ErrorType;
import com.Lubee.Lubee.common.exception.RestApiException;
import com.Lubee.Lubee.location.domain.Location;
import com.Lubee.Lubee.location.repository.LocationRepository;
import com.Lubee.Lubee.memory.domain.Memory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Location getLocationByMemory(Memory memory)
    {
        return locationRepository.findByMemories(memory).orElseThrow(
                () -> new RestApiException(ErrorType.NOT_FOUND_LOCATION)
        );
    }
}
