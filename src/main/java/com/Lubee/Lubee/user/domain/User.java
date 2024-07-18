package com.Lubee.Lubee.user.domain;

import com.Lubee.Lubee.common.BaseEntity;
import com.Lubee.Lubee.common.enumSet.LoginType;
import com.Lubee.Lubee.common.enumSet.UserRoleEnum;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.date_comment.domain.DateComment;
import com.Lubee.Lubee.enumset.Profile;
import com.Lubee.Lubee.firebase.domain.FireBase;
import com.Lubee.Lubee.user.dto.SignupDto;
import com.Lubee.Lubee.user_calendar_memory.domain.UserCalendarMemory;
import com.Lubee.Lubee.user_memory.domain.UserMemory;
import com.Lubee.Lubee.user_memory_reaction.domain.UserMemoryReaction;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long id;

    // 여기서 카카오 아이디가 들어간다
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date birthday;

    private Profile profile;

    private String nickname;

    private boolean alreadyCouple = false;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

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

    public void linkCouple(Couple couple) {         // 커플 연결됐을 때 user 정보 변경
        this.couple = couple;
        this.alreadyCouple = true;
    }
    public void UserSignup(User user, SignupDto signupDto)
    {
        user.setNickname(signupDto.getNickname());
        user.setProfile(signupDto.getProfile());
        user.setBirthday(signupDto.getBirthday());
    }

}
