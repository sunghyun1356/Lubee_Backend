package com.Lubee.Lubee.enumset;

import lombok.Getter;

@Getter
public enum Category {
    RESTAURANT("맛집"),
    CAFE("카페"),
    CULTURE("문화"),
    ETC("etc");

    private final String CategoryName;

    Category(String categoryName) {
        this.CategoryName = categoryName;
    }

}