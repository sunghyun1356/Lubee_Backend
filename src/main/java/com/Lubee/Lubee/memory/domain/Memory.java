package com.Lubee.Lubee.memory.domain;

import com.Lubee.Lubee.calendar_memory.domain.CalendarMemory;
import com.Lubee.Lubee.common.BaseEntity;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.date_comment.domain.DateComment;
import com.Lubee.Lubee.location.domain.Location;
import com.Lubee.Lubee.user_memory.domain.UserMemory;
import com.Lubee.Lubee.user_memory_reaction.domain.UserMemoryReaction;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memory_id")
    private Long memory_id;


    private String content;

    private String locationName;

    private Date time;

    private String picture;


    @ManyToOne
    @JoinColumn(name = "user_memory_id", nullable = false)
    private UserMemory userMemory;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "couple_id", nullable = false)
    private Couple couple;

    @OneToMany(mappedBy = "memory")
    private List<CalendarMemory> calendarMemories;

    @OneToMany(mappedBy = "memory")
    private List<UserMemoryReaction> userMemoryReactions;

}