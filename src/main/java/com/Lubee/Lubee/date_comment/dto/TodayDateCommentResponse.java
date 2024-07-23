package com.Lubee.Lubee.date_comment.dto;

import com.Lubee.Lubee.date_comment.domain.DateComment;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 추가
@ToString
public class TodayDateCommentResponse {

    private DateCommentBaseDto mine;
    private DateCommentBaseDto lover;

}
