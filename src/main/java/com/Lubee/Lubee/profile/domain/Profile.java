package com.Lubee.Lubee.profile.domain;

import com.Lubee.Lubee.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Column(name = "profile_url")
    private String profielUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "userProfile")
    private List<User> user = new ArrayList<>();

    public static Profile of(String profileUrl) {
        Profile profile = new Profile();
        profile.setProfielUrl(profileUrl);
        return profile;
    }

}