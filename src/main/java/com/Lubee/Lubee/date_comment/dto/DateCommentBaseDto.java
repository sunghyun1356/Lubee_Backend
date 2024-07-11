package com.Lubee.Lubee.date_comment.dto;

import com.Lubee.Lubee.enumset.Profile;
import lombok.*;

@Data
@NoArgsConstructor
@ToString
@Getter
public class DateCommentBaseDto {
    private String content;
    private Profile profile;
}
