package com.Lubee.Lubee.location.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeoulLocationInfo {

    private String name;
    private String parcelBaseAddress;
    private String x_coord;
    private String y_coord;

    @Builder
    public SeoulLocationInfo(String name, String parcelBaseAddress, String x_coord, String y_coord) {
        this.name = name;
        this.parcelBaseAddress = parcelBaseAddress;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
    }
}
