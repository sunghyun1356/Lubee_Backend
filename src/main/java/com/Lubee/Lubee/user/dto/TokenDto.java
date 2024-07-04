package com.Lubee.Lubee.user.dto;

import lombok.*;

@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String message;
    private String accessToken;
    private String refreshToken;
}
