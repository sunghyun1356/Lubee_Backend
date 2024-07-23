package com.Lubee.Lubee.enumset;

import lombok.Getter;

@Getter
public enum Profile {
    a("1"),
    b("b"),
    c("c"),
    d("d");

    private final String ProfileName;

    Profile(String profileName) {
        this.ProfileName = profileName;
    }

}
