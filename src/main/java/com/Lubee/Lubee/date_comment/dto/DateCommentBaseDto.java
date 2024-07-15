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

    public DateCommentBaseDto(String content, Profile profile) {
        this.content = content;
        this.profile = profile;
    }

    public static DateCommentBaseDto of(String content, Profile profile) {
        return new DateCommentBaseDto(content, profile);
    }
}
