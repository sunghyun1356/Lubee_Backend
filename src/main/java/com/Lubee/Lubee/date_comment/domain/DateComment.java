package com.Lubee.Lubee.date_comment.domain;

import com.Lubee.Lubee.common.BaseEntity;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "memory_id", nullable = false)
    private Memory memory;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String content;
}