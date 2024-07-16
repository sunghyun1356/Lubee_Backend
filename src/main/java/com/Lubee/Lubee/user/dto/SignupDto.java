package com.Lubee.Lubee.user.dto;

import com.Lubee.Lubee.profile.domain.Profile;
import lombok.*;

import java.util.Date;

@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
    String nickname;
    String profile;
    Date birthday;
}
