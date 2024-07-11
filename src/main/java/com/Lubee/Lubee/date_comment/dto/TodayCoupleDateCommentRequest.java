package com.Lubee.Lubee.date_comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class TodayCoupleDateCommentRequest {

    private Long userId;
    private Long coupleId;
    //private Long calendarId;
    private Date date;

}
