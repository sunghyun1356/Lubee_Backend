package com.Lubee.Lubee.date_comment.domain;

import com.Lubee.Lubee.calendar.domain.Calendar;
import com.Lubee.Lubee.common.BaseEntity;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DateComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "couple_id", nullable = false)
    private Couple couple;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id", nullable = false)
    private Calendar calendar;      //calendar, datecomment는 양방향 일대다

    @Builder
    public DateComment(User user, Couple couple, String content, Calendar calendar){
        this.user = user;
        this.couple = couple;
        this.content = content;
        this.calendar = calendar;
    }

    public void changeContent(String content){
        this.content = content;
    }

}