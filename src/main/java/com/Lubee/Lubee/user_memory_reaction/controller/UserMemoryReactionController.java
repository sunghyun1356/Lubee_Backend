package com.Lubee.Lubee.user_memory_reaction.controller;

import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.common.api.SuccessResponse;
import com.Lubee.Lubee.enumset.Reaction;
import com.Lubee.Lubee.user_memory_reaction.service.UserMemoryReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reactions")
public class UserMemoryReactionController {

    private UserMemoryReactionService userMemoryReactionService;

    @PostMapping("/{userMemoryReactionId}")
    public ApiResponseDto<SuccessResponse> createReaction(@AuthenticationPrincipal UserDetails userDetails, Long userMemoryReactionId, Reaction reaction)
    {
        return userMemoryReactionService(userDetails, userMemoryReactionId, reaction);
    }
    @DeleteMapping("/{userMemoryReactionId}")
    public ApiResponseDto<SuccessResponse> deleteReaction(@AuthenticationPrincipal UserDetails userDetails,Long userMemoryReactionId)
    {
        return userMemoryReactionService(userDetails, userMemoryReactionId);
    }

}
