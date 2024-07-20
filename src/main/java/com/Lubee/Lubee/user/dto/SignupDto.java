package com.Lubee.Lubee.user.dto;

import com.Lubee.Lubee.enumset.Profile;
import lombok.*;

import java.util.Date;

@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
    String nickname;
    Profile profile;
    Date birthday;
}
