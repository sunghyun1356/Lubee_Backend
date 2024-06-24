package com.Lubee.Lubee.user_memory.domain;

import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UserMemory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "memory_id")
    private Memory memory;
}
