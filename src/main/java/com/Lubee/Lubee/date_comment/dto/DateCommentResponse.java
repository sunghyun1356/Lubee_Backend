package com.Lubee.Lubee.date_comment.dto;

import com.Lubee.Lubee.date_comment.domain.DateComment;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class DateCommentResponse {

    private Long userId;
    private String content;

    public static DateCommentResponse of(DateComment dateComment) {
        return new DateCommentResponse(
                dateComment.getUser().getId(),
                dateComment.getContent()
        );
    }

}
