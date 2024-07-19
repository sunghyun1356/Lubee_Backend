package com.Lubee.Lubee.couple.controller;

import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.couple.dto.LubeeCodeResponse;
import com.Lubee.Lubee.couple.service.CoupleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/couples")
public class CoupleController {

    private final CoupleService coupleService;

    /**
     * 러비코드 생성
     *
     * @param userDetails 인증된 사용자의 정보를 담고 있는 UserDetails 객체
     * @return ApiResponseDto<LubeeCodeResponse>  생성된 LubeeCode의 id를 포함
     */
    @PostMapping("/lubee-code")
    public ApiResponseDto<LubeeCodeResponse> generateLubeeCode(@AuthenticationPrincipal UserDetails userDetails){

        return coupleService.generateLubeeCode(userDetails);
    }

    /**
     * 러비코드 조회
     *
     * @param id User의 id
     * @return ApiResponseDto<LubeeCodeResponse>  생성된 LubeeCode의 id를 포함
     */
    @GetMapping("/lubee-code/{id}")
    public ApiResponseDto<LubeeCodeResponse> getLubeeCode(@PathVariable Long id){

        return coupleService.findLubeeCode(id);
    }

    /**
     * 커플 연결
     *
     * @param userDetails 인증된 사용자의 정보를 담고 있는 UserDetails 객체
     * @param inputCode 사용자가 입력한 코드
     * @return ApiResponseDto<LubeeCodeResponse>  생성된 LubeeCode의 id를 포함
     */
    @PostMapping("/link")
    public ApiResponseDto<Long> linkCouple(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String inputCode) {

        return coupleService.linkCouple(userDetails, inputCode);
    }

}