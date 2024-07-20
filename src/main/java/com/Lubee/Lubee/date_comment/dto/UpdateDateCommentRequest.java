package com.Lubee.Lubee.date_comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateDateCommentRequest {

    private Long dateCommentId;
    private String content;

    public UpdateDateCommentRequest(Long dateCommentId, String content) {
        this.dateCommentId = dateCommentId;
        this.content = content;
    }
}
