package com.Lubee.Lubee.anniversary.dto;

import com.Lubee.Lubee.couple.dto.CoupleInfoDto;
import com.Lubee.Lubee.enumset.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@ToString
public class AnniversaryListDto {
    List<AnniversaryDto> anniversaryDtoList;

    public static AnniversaryListDto of( List<AnniversaryDto> anniversaryDtoList) {

        return new AnniversaryListDto(anniversaryDtoList);
    }
}
