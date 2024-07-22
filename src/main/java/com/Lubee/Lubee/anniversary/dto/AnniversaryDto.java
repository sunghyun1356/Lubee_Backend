package com.Lubee.Lubee.anniversary.dto;

import com.Lubee.Lubee.couple.dto.CoupleInfoDto;
import com.Lubee.Lubee.enumset.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class AnniversaryDto {
    private String anniversary_title;
    private Date anniversary_date;


    public static AnniversaryDto of(String anniversary_title, Date anniversary_date) {

        return new AnniversaryDto(anniversary_title, anniversary_date);
    }
}
