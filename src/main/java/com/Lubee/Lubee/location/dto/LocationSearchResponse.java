package com.Lubee.Lubee.location.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class LocationSearchResponse {

    private int totalNum;   // 검색 결과 개수
    private List<LocationDto> locations;

    public LocationSearchResponse(List<LocationDto> locations) {
        totalNum = locations.size();
        this.locations = locations;
    }
}
