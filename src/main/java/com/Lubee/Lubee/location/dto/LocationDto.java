package com.Lubee.Lubee.location.dto;

import com.Lubee.Lubee.location.domain.Location;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocationDto {

    private Long locationId;
    private String name;
    private String parcelBaseAddress;

    @Builder
    public LocationDto(Long locationId, String name, String parcelBaseAddress) {
        this.locationId = locationId;
        this.name = name;
        this.parcelBaseAddress = parcelBaseAddress;
    }

    public static LocationDto from(Location location) {
        return new LocationDto(
                location.getLocation_id(),
                location.getName(),
                location.getParcelBaseAddress()
        );
    }

}
