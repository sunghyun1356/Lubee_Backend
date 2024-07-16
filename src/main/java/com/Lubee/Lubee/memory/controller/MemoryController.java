package com.Lubee.Lubee.memory.controller;

import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.common.api.SuccessResponse;
import com.Lubee.Lubee.memory.dto.HomeDto;
import com.Lubee.Lubee.memory.dto.MemoryBaseDto;
import com.Lubee.Lubee.memory.dto.MemoryCreateRequestDto;
import com.Lubee.Lubee.memory.facade.MemoryFacade;
import com.Lubee.Lubee.memory.service.MemoryService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/memories")
public class MemoryController {

    private final MemoryFacade memoryFacade;
    private final MemoryService memoryService;
     @GetMapping("/home")
    public ApiResponseDto<HomeDto>Home(@AuthenticationPrincipal UserDetails loginUser)
    {
        return memoryFacade.getHomeInfo(loginUser);
    }
    @PostMapping("/create")
    public ApiResponseDto<SuccessResponse>create_memory(@AuthenticationPrincipal UserDetails loginUser, MemoryCreateRequestDto memoryRequest )
    {
        return memoryFacade.createMemory(loginUser, memoryRequest);
    }
    @GetMapping("/memory/{memory_id}")
    public ApiResponseDto<MemoryBaseDto>getOneMemory(@AuthenticationPrincipal UserDetails loginUser, Long memoryId)
    {
        return memoryFacade.getOneMemory(loginUser, memoryId);
    }
    @DeleteMapping("/memory/{memory_id}")
    public ApiResponseDto<SuccessResponse>deleteOneMemory(@AuthenticationPrincipal UserDetails loginUser, Long memoryId)
    {
        return memoryFacade.deleteMemory(loginUser, memoryId);
    }

}
