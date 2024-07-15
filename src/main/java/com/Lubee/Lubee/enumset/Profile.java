package com.Lubee.Lubee.enumset;

import lombok.Getter;

@Getter
public enum Profile {
    first("첫번쨰"),
    second("두번쨰"),
    third("세번쨰"),
    ETC("etc");

    private final String displayName;

    Profile(String displayName) {
        this.displayName = displayName;
    }

}
