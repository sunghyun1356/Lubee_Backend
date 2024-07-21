package com.Lubee.Lubee.user.service;

import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.common.api.ErrorResponse;
import com.Lubee.Lubee.common.api.ResponseUtils;
import com.Lubee.Lubee.common.api.SuccessResponse;
import com.Lubee.Lubee.common.enumSet.ErrorType;
import com.Lubee.Lubee.common.exception.RestApiException;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.couple.repository.CoupleRepository;
import com.Lubee.Lubee.user.domain.User;
import com.Lubee.Lubee.user.dto.SignupDto;
import com.Lubee.Lubee.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CoupleRepository coupleRepository;

    // 추후 수정
    public User getUser(UserDetails loginUser)
    {

        return userRepository.findByUsername(loginUser.getUsername())
                .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_USER));
    }
    public ApiResponseDto<SuccessResponse> onBoarding(UserDetails loginUser, SignupDto signupDto)
    {
        User user = userRepository.findUserByUsername(loginUser.getUsername()).orElseThrow(
                () ->  new RestApiException(ErrorType.NOT_FOUND_USER)
        );
        user.setProfile(signupDto.getProfile());
        user.setNickname(signupDto.getNickname());
        user.setBirthday(signupDto.getBirthday());
        Couple couple = coupleRepository.findCoupleByUser(user).orElseThrow(
                () -> new RestApiException(ErrorType.NOT_FOUND_COUPLE)
        );
        couple.setStartDate(signupDto.getStartDate());
        userRepository.save(user);
        coupleRepository.save(couple);
        return ResponseUtils.ok(SuccessResponse.of(HttpStatus.OK, "온보딩 완료"), ErrorResponse.builder().status(200).message("요청 성공").build());
    }
}
