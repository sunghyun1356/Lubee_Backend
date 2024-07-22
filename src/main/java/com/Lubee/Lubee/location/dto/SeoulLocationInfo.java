package com.Lubee.Lubee.location.dto;

import com.Lubee.Lubee.enumset.Category;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeoulLocationInfo {

    private String name;
    private String parcelBaseAddress;
    private String x_coord;
    private String y_coord;
    private Category category;

    @Builder
    public SeoulLocationInfo(String name, String parcelBaseAddress, String x_coord, String y_coord, Category category) {
        this.name = name;
        this.parcelBaseAddress = parcelBaseAddress;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.category = category;
    }
}
