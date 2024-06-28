package com.Lubee.Lubee.anniversary.domain;

import com.Lubee.Lubee.couple.domain.Couple;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Anniversary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long anniversary_id;

    // 기념일 이름
    private String title;
    // 기념일 날짜
    private Date anniversary_date;

    @ManyToOne
    @JoinColumn(name = "couple_id", nullable = false)
    private Couple couple;
}
