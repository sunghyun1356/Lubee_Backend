package com.Lubee.Lubee.date_comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateDateCommentRequest {

    private Long datecommentId;
    private String content;

    public UpdateDateCommentRequest(Long datecommentId, String content) {
        this.datecommentId = datecommentId;
        this.content = content;
    }
}
