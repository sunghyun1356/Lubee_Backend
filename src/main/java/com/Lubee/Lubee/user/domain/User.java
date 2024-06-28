package com.Lubee.Lubee.user.domain;

import com.Lubee.Lubee.calendar_memory.domain.CalendarMemory;
import com.Lubee.Lubee.common.BaseEntity;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.date_comment.domain.DateComment;
import com.Lubee.Lubee.enumset.Profile;
import com.Lubee.Lubee.firebase.domain.FireBase;
import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.user_memory.domain.UserMemory;
import com.Lubee.Lubee.user_memory_reaction.domain.UserMemoryReaction;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long id;

    // 로그인 시 필요한 username
    @Column(nullable = false)
    private String username;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    // 이메일
    @Column(nullable = false, unique = true)
    private String email;

    // 생일
    private Date birthday;

    // 프로필
    private Profile profile;

    // 닉네임
    private String nickname;

    // 커플 인증 여부
    private boolean alreadyCouple;

    @OneToMany(mappedBy = "user")
    private List<DateComment> dateComments;

    @OneToMany(mappedBy = "user")
    private List<UserMemory> userMemories;

    @ManyToOne
    @JoinColumn(name = "couple_id", nullable = false)
    private Couple couple;

    @OneToMany(mappedBy = "user")
    private List<UserMemoryReaction> userMemoryReactions;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private FireBase fireBase;


}
