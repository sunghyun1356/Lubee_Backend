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

    private String title;
    private Date anniversary_date;

    @ManyToOne
    @JoinColumn(name = "couple_id", nullable = false)
    private Couple couple;
}
