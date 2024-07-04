package com.Lubee.Lubee.user.controller;

import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.common.oauth.OauthService;
import com.Lubee.Lubee.user.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    final private OauthService oAuthService;

    @PostMapping("/kakao")
    public ApiResponseDto<TokenDto> kakaoLogin(@RequestParam("kakaoAccessToken") String kakaoAccessToken) {

        return oAuthService.kakaoLogin(kakaoAccessToken);
    }

    @PostMapping("/kakao/refresh")
    public ApiResponseDto<TokenDto> kakaorefreshToken(
            @RequestHeader(value="accessToken") String accessToken,
            @RequestHeader(value="refreshToken") String refreshToken
    ) {
        return oAuthService.refreshKakaoToken(accessToken, refreshToken);
    }

}
