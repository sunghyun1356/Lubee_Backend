package com.Lubee.Lubee.user.controller;

import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.common.api.SuccessResponse;
import com.Lubee.Lubee.common.oauth.OauthService;
import com.Lubee.Lubee.user.dto.SignupDto;
import com.Lubee.Lubee.user.dto.TokenDto;
import com.Lubee.Lubee.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    final private OauthService oAuthService;
    final private UserService userService;
    @GetMapping("/test")
    public Boolean test()
    {
        return oAuthService.test();
    }

    // kakao로그인을 통해서 유저 기록이 있으면 바로 로그인을 진행한다
    // 커플 연동에 대한 여부를 확인한 뒤 바로 회원가입을 진행한다.
    @PostMapping("/kakao")
    public ApiResponseDto<TokenDto> kakaoLogin(@RequestParam("code") String kakaoAccessToken,
                                               @RequestBody SignupDto signupDto,
                                               @RequestBody Date startDate) {

        if (signupDto == null) {
            signupDto = new SignupDto(); // 빈 객체 생성
        }
        if (startDate == null)
        {
            startDate = new Date();
        }
        return oAuthService.kakaoLoginOrSignup(kakaoAccessToken, signupDto, startDate);
    }

    @PostMapping("/kakao/simpleLogin")
    public ApiResponseDto<TokenDto> kakaoSimple(
            @RequestParam("code") String kakaoAccessToken
    )
    {
        if (kakaoAccessToken == null || kakaoAccessToken.isEmpty()) {
            throw new IllegalArgumentException("Kakao access token is required");
        }
        return oAuthService.kakaoLoginOrSignupSimple(kakaoAccessToken);
    }

    @PostMapping("/kakao/simpleLoginOld")
    public ApiResponseDto<TokenDto> kakaoSimpleOld(
            @RequestParam("code") String kakaoAccessToken
    )
    {
        if (kakaoAccessToken == null || kakaoAccessToken.isEmpty()) {
            throw new IllegalArgumentException("Kakao access token is required");
        }
        return oAuthService.kakaoSimpleOld(kakaoAccessToken);
    }


    @PostMapping("/kakao/refresh")
    public ApiResponseDto<TokenDto> kakaorefreshToken(
            @RequestHeader(value="accessToken") String accessToken,
            @RequestHeader(value="refreshToken") String refreshToken
    ) {
        return oAuthService.refreshKakaoToken(accessToken, refreshToken);
    }
    @PostMapping("/onBoarding")
    public ApiResponseDto<SuccessResponse> onBoarding(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody SignupDto signupDto
    )
    {
        return userService.onBoarding(userDetails, signupDto);
    }

}
