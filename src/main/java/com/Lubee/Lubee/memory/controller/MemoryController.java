package com.Lubee.Lubee.memory.controller;

import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.common.api.SuccessResponse;
import com.Lubee.Lubee.enumset.Reaction;
import com.Lubee.Lubee.memory.dto.HomeDto;
import com.Lubee.Lubee.memory.dto.MemoryBaseDto;
import com.Lubee.Lubee.memory.dto.MemoryCreateRequestDto;
import com.Lubee.Lubee.memory.facade.MemoryFacade;
import com.Lubee.Lubee.memory.service.MemoryService;
import com.Lubee.Lubee.user_memory_reaction.service.UserMemoryReactionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/api/memories")
public class MemoryController {

    private final MemoryFacade memoryFacade;
    private final MemoryService memoryService;
    private final UserMemoryReactionService userMemoryReactionService;
    @GetMapping("/home")
    public ApiResponseDto<HomeDto> home(@AuthenticationPrincipal UserDetails loginUser) {
        return memoryFacade.getHomeInfo(loginUser);
    }

    @PostMapping("/create")
    public ApiResponseDto<SuccessResponse> createMemory(@AuthenticationPrincipal UserDetails loginUser, @RequestPart("picture") MultipartFile file, @RequestParam Long location_id) {
        return memoryFacade.createMemory(loginUser, file, location_id);
    }

    @GetMapping("/")
    public ApiResponseDto<MemoryBaseDto> getOneMemory(@AuthenticationPrincipal UserDetails loginUser, @RequestParam("memory_id") Long memory_id) {
        return memoryFacade.getOneMemory(loginUser, memory_id);
    }

    @DeleteMapping("/")
    public ApiResponseDto<SuccessResponse> deleteOneMemory(@AuthenticationPrincipal UserDetails loginUser, @RequestParam Long memory_id) {
        return memoryFacade.deleteMemory(loginUser, memory_id);
    }

    @PostMapping("/reaction/")
    public ApiResponseDto<SuccessResponse> createReaction(@AuthenticationPrincipal UserDetails loginUser, @RequestParam Long memory_id, @RequestParam Reaction reaction) {
        return userMemoryReactionService.createReaction(loginUser, memory_id, reaction);
    }

    @DeleteMapping("/reaction/")
    public ApiResponseDto<SuccessResponse> deleteReaction(@AuthenticationPrincipal UserDetails loginUser, @RequestParam Long memory_id) {
        return userMemoryReactionService.deleteReaction(loginUser, memory_id);
    }

}
