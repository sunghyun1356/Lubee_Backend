package com.Lubee.Lubee.user_calendar_memory.domain;

import com.Lubee.Lubee.calendar_memory.domain.CalendarMemory;
import com.Lubee.Lubee.common.BaseEntity;
import com.Lubee.Lubee.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UserCalendarMemory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "calendar_memory_id")
    private CalendarMemory calendarMemory;

    private Long view;
}
