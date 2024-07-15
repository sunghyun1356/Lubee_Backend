package com.Lubee.Lubee.memory.controller;

import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.memory.dto.HomeDto;
import com.Lubee.Lubee.memory.facade.MemoryFacade;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/memories")
public class MemoryController {

    private static MemoryFacade memoryFacade;
    @GetMapping("/home")
    public ApiResponseDto<HomeDto>Home(@AuthenticationPrincipal UserDetails loginUser)
    {
        return memoryFacade.getHomeInfo(loginUser);
    }
}
