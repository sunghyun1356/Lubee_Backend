package com.Lubee.Lubee.user_memory_reaction.domain;

import com.Lubee.Lubee.common.BaseEntity;
import com.Lubee.Lubee.enumset.Reaction;
import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.user.domain.User;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMemoryReaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_memory_reaction_id")
    private Long user_memory_reaction_id;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "memory_id", nullable = false)
    private Memory memory;

    private Reaction reaction;
}
