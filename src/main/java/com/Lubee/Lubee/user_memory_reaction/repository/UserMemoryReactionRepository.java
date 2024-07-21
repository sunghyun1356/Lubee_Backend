package com.Lubee.Lubee.user_memory_reaction.repository;

import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.user.domain.User;
import com.Lubee.Lubee.user_memory_reaction.domain.UserMemoryReaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMemoryReactionRepository extends JpaRepository<UserMemoryReaction,Long> {
    List<UserMemoryReaction> getUserMemoryReactionByMemory(Memory memory);

    List<UserMemoryReaction> getUserMemoryReactionByUserAndMemory(User user, Memory memory);

    UserMemoryReaction getUserMemoryReactionOneByUserAndMemory(User user, Memory memory);
}
