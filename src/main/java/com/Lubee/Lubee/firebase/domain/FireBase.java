package com.Lubee.Lubee.firebase.domain;

import com.Lubee.Lubee.common.BaseEntity;
import com.Lubee.Lubee.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FireBase extends BaseEntity {

    // mysql과 연결된 user_id와 연결
    @Id
    @Column(name = "user_id")
    private Long userId;

    private String firebaseToken;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}