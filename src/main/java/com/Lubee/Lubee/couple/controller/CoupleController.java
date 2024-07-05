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

    @PostMapping("/lubee-code")
    public ApiResponseDto<LubeeCodeResponse> generateLubeeCode(@AuthenticationPrincipal UserDetails userDetails){

        return coupleService.generateLubeeCode(userDetails);
    }

    @GetMapping("/lubee-code/{id}")
    public ApiResponseDto<LubeeCodeResponse> getLubeeCode(@PathVariable Long id){

        return coupleService.findLubeeCode(id);
    }

    @PostMapping("/link")
    public ApiResponseDto<Long> linkCouple(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String inputCode) {

        return coupleService.linkCouple(userDetails, inputCode);
    }

}