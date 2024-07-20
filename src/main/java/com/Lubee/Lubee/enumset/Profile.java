package com.Lubee.Lubee.enumset;

import lombok.Getter;

@Getter
public enum Profile {
    a("a"),
    b("b"),
    c("c"),
    d("d");

    private final String displayName;

    Profile(String displayName) {
        this.displayName = displayName;
    }

}
