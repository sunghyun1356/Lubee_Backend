package com.Lubee.Lubee.couple.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class LubeeCodeResponse {

    private String code;

    public static LubeeCodeResponse of(String code) {

        return new LubeeCodeResponse(code);
    }

}
