package com.Lubee.Lubee.enumset;

import lombok.Getter;

@Getter
public enum Profile {
    HONGDAE_YEONNAM("홍대/연남"),
    HAPJEONG("합정"),
    SINCHON("신촌"),
    ETC("etc");

    private final String displayName;

    Profile(String displayName) {
        this.displayName = displayName;
    }

}
