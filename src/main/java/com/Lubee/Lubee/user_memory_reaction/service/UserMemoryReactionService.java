package com.Lubee.Lubee.user_memory_reaction.service;

import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.common.api.SuccessResponse;
import com.Lubee.Lubee.common.enumSet.ErrorType;
import com.Lubee.Lubee.common.exception.RestApiException;
import com.Lubee.Lubee.enumset.Reaction;
import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.user_memory_reaction.domain.UserMemoryReaction;
import com.Lubee.Lubee.user_memory_reaction.repository.UserMemoryReactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public List<UserMemoryReaction> getUserMemoryReactionsByMemory(Memory memory)
    {
        return userMemoryReactionRepository.getUserMemoryReactionByMemory(memory);
    }
//    public ApiResponseDto<SuccessResponse>createReaction(UserDetails loginUser, Long userMemoryReactionId, Reaction reaction)
//    {
//        UserMemoryReaction userMemoryReaction = userMemoryReactionRepository.findById(userMemoryReactionId).orElseThrow(
//                () -> new RestApiException(ErrorType.NOT_FOUND_USERMEMORYREACTION)
//        );        userMemoryReaction.setReaction(reaction);
//        userMemoryReactionRepository.save(userMemoryReaction);
//    }
//    public ApiResponseDto<SuccessResponse>createReaction(UserDetails loginUser, Long userMemoryReactionId)
//    {
//        UserMemoryReaction userMemoryReaction = userMemoryReactionRepository.findById(userMemoryReactionId).orElseThrow(
//                () -> new RestApiException(ErrorType.NOT_FOUND_USERMEMORYREACTION)
//        );
//        userMemoryReactionRepository.save(userMemoryReaction);
//    }

}
