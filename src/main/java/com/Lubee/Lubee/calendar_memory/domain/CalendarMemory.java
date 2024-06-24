package com.Lubee.Lubee.calendar_memory.domain;


import com.Lubee.Lubee.calendar.domain.Calendar;
import com.Lubee.Lubee.common.BaseEntity;
import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalendarMemory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_memory_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "calendar_id", nullable = false)
    private Calendar calendar;

    @ManyToOne
    @JoinColumn(name = "memory_id", nullable = false)
    private Memory memory;

}