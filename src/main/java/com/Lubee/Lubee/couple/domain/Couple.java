package com.Lubee.Lubee.couple.domain;

import com.Lubee.Lubee.anniversary.domain.Anniversary;
import com.Lubee.Lubee.calendar.domain.Calendar;
import com.Lubee.Lubee.date_comment.domain.DateComment;
import com.Lubee.Lubee.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date startDate;

    private boolean subscribe;

    private Long total_honey;

    private int present_honey;

    @OneToMany(mappedBy = "couple")  // 양방향 연관 관계에서 주인 설정
    //@JoinColumn(name = "user_id", nullable = false)
    private List<User> user = new ArrayList<>();

    @OneToMany(mappedBy = "couple")
    private List<Calendar> calendars;

    @OneToMany(mappedBy = "couple")
    private List<DateComment> dateComments;

    @OneToMany(mappedBy = "couple")
    private List<Anniversary> anniversaries;

    @Builder
    public Couple(User requester, User receiver) {
        user.add(requester);
        user.add(receiver);
        this.subscribe = false;
        this.total_honey = 0L;
        this.present_honey = 0;
    }
    public void setting_start(Couple couple, Date startDate)
    {
        this.startDate = startDate;
    }

}