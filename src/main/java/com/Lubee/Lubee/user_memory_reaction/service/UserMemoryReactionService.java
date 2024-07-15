package com.Lubee.Lubee.user_memory_reaction.service;

import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.user_memory_reaction.domain.UserMemoryReaction;
import com.Lubee.Lubee.user_memory_reaction.repository.UserMemoryReactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
