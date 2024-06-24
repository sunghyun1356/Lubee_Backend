package com.Lubee.Lubee.couple.domain;

import com.Lubee.Lubee.anniversary.domain.Anniversary;
import com.Lubee.Lubee.calendar.domain.Calendar;
import com.Lubee.Lubee.date_comment.domain.DateComment;
import com.Lubee.Lubee.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Couple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "couple_id")
    private Long id;

    private Date startDate;

    private boolean subscribe;

    private Long total_honey;

    private int present_honey;

    @OneToMany
    @JoinColumn(name = "user_id", nullable = false)
    private List<User> user;

    @OneToMany(mappedBy = "couple")
    private List<Calendar> calendars;

    @OneToMany(mappedBy = "couple")
    private List<DateComment> dateComments;

    @OneToMany(mappedBy = "couple")
    private List<Anniversary> anniversaries;
}