package com.Lubee.Lubee.user.dto;

import com.Lubee.Lubee.enumset.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date today;
    private Date birthday;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
}
