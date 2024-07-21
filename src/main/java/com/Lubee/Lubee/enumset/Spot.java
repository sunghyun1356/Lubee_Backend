package com.Lubee.Lubee.enumset;

import lombok.Getter;

@Getter
public enum Spot {
    HONGDAE_YEONNAM("홍대/연남"),
    HAPJEONG("합정"),
    SINCHON("신촌"),
    ETC("etc");

    private final String SpotName;

    Spot(String spotName) {
        this.SpotName = spotName;
    }

}
