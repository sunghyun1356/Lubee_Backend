package com.Lubee.Lubee.user_memory_reaction.service;

import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.common.api.ErrorResponse;
import com.Lubee.Lubee.common.api.ResponseUtils;
import com.Lubee.Lubee.common.api.SuccessResponse;
import com.Lubee.Lubee.common.enumSet.ErrorType;
import com.Lubee.Lubee.common.exception.RestApiException;
import com.Lubee.Lubee.enumset.Reaction;
import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.memory.repository.MemoryRepository;
import com.Lubee.Lubee.user.domain.User;
import com.Lubee.Lubee.user.repository.UserRepository;
import com.Lubee.Lubee.user_memory_reaction.domain.UserMemoryReaction;
import com.Lubee.Lubee.user_memory_reaction.repository.UserMemoryReactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserMemoryReactionService {
    private final UserMemoryReactionRepository userMemoryReactionRepository;
    private final UserRepository userRepository;
    private final MemoryRepository memoryRepository;
    public List<UserMemoryReaction> getUserMemoryReactionsByMemory(Memory memory)
    {
        return userMemoryReactionRepository.getUserMemoryReactionByMemory(memory);
    }
    public ApiResponseDto<SuccessResponse>createReaction(UserDetails loginUser, Long memory_id, Reaction reaction)
    {
        User user = userRepository.findUserByUsername(loginUser.getUsername()).orElseThrow(
                () -> new RestApiException(ErrorType.NOT_FOUND_USER)
        );
        Memory memory = memoryRepository.findById(memory_id).orElseThrow(
                () -> new RestApiException(ErrorType.NOT_FOUND_MEMORY)
        );
        UserMemoryReaction userMemoryReaction = UserMemoryReaction.of(user,memory, reaction);
        userMemoryReaction.setReaction(reaction);
        userMemoryReactionRepository.save(userMemoryReaction);
        return ResponseUtils.ok(SuccessResponse.of(HttpStatus.OK, "Reaction 생성이 완료되었습니다"), ErrorResponse.builder().status(200).message("요청 성공").build());
    }
    public ApiResponseDto<SuccessResponse>deleteReaction(UserDetails loginUser, Long memory_id)
    {
        User user = userRepository.findUserByUsername(loginUser.getUsername()).orElseThrow(
                () -> new RestApiException(ErrorType.NOT_FOUND_USER)
        );
        Memory memory = memoryRepository.findById(memory_id).orElseThrow(
                () -> new RestApiException(ErrorType.NOT_FOUND_MEMORY)
        );
        UserMemoryReaction userMemoryReaction = userMemoryReactionRepository.getUserMemoryReactionOneByUserAndMemory(user, memory);
        userMemoryReactionRepository.delete(userMemoryReaction);
        return ResponseUtils.ok(SuccessResponse.of(HttpStatus.OK, "Reaction 삭제가 완료되었습니다"), ErrorResponse.builder().status(200).message("요청 성공").build());
    }

}
