package com.Lubee.Lubee.user.domain;

import com.Lubee.Lubee.calendar_memory.domain.CalendarMemory;
import com.Lubee.Lubee.common.BaseEntity;
import com.Lubee.Lubee.common.enumSet.LoginType;
import com.Lubee.Lubee.common.enumSet.UserRoleEnum;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.date_comment.domain.DateComment;
import com.Lubee.Lubee.enumset.Profile;
import com.Lubee.Lubee.firebase.domain.FireBase;
import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.user_calendar_memory.domain.UserCalendarMemory;
import com.Lubee.Lubee.user_memory.domain.UserMemory;
import com.Lubee.Lubee.user_memory_reaction.domain.UserMemoryReaction;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private int year;

    private Date birthday;

    private Profile profile;

    private String nickname;

    private boolean alreadyCouple;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user")
    private List<DateComment> dateComments;

    @OneToMany(mappedBy = "user")
    private List<UserMemory> userMemories;

    @OneToMany(mappedBy = "user")
    private List<UserCalendarMemory> userCalendarMemories;

    @ManyToOne
    @JoinColumn(name = "couple_id", nullable = false)
    private Couple couple;

    @OneToMany(mappedBy = "user")
    private List<UserMemoryReaction> userMemoryReactions;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private FireBase fireBase;

    private LoginType loginType;

    // 카카오 로그인 refresh 토큰
    private String kakaoRefreshToken;

    @Builder
    public User(LoginType socialType, String socialId) {
        this.loginType = socialType;
        this.username = socialId;
    }
    public User(String username, String email, String userPuzzleId, UserRoleEnum role, String refreshToken, LoginType loginType )
    {
        this.username = username;
        this.email = email;
        this.role = role;
        this.kakaoRefreshToken = refreshToken;
        this.loginType = loginType;

    }
    public static User of(LoginType loginType,  String username, String password, UserRoleEnum role) {
        User user = new User(loginType, username); // 일반 로그인 타입으로 사용자 생성
        user.setPassword(password); // 패스워드 설정
        user.setRole(role); // 역할 설정
        return user; // 사용자 반환
    }

}
