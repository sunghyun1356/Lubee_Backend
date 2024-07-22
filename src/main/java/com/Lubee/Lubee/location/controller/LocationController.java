package com.Lubee.Lubee.location.controller;

import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.location.dto.LocationSearchResponse;
import com.Lubee.Lubee.location.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/search")
    public ApiResponseDto<LocationSearchResponse> searchLocation(@RequestParam String keyword) {

        return locationService.findByKeyword(keyword);
    }

}
