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

    private String name;
    private String parcelBaseAddress;

    @Builder
    public LocationDto(String name, String parcelBaseAddress) {
        this.name = name;
        this.parcelBaseAddress = parcelBaseAddress;
    }

    public static LocationDto from(Location location) {
        return new LocationDto(
                location.getName(),
                location.getParcelBaseAddress()
        );
    }

}
