package com.Lubee.Lubee.calendar.domain;

import com.Lubee.Lubee.calendar_memory.domain.CalendarMemory;
import com.Lubee.Lubee.common.BaseEntity;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.date_comment.domain.DateComment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calendar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "couple_id", nullable = false)
    private Couple couple;

    @Temporal(TemporalType.DATE)
    private Date eventDate;

    private String description;

    @OneToMany(mappedBy = "calendar")
    private List<CalendarMemory> calendarMemories;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DateComment> dateComments = new ArrayList<>();


    @Builder
    public Calendar(Couple couple, Date eventDate) {
        this.couple = couple;
        this.eventDate = eventDate;
    }

    public void addDateComment(DateComment dateComment) {
        dateComments.add(dateComment);
    }

}