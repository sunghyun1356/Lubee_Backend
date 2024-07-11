package com.Lubee.Lubee.date_comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class CreateDateCommentRequest {

    private String content;
    private Long coupleId;
    //private Long calendarId;
    private Date date;

    public CreateDateCommentRequest(String content, Long coupleId, Date date) {
        this.content = content;
        this.coupleId = coupleId;
        this.date = date;
    }
}
