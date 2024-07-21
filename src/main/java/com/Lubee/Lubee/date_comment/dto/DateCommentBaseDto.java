package com.Lubee.Lubee.date_comment.dto;

import com.Lubee.Lubee.enumset.Profile;
import lombok.*;

@Data
@NoArgsConstructor
@ToString
@Getter
public class DateCommentBaseDto {
    private Long user_id;
    private String content;
    private Profile profile;

    public DateCommentBaseDto(Long user_id,String content, Profile profile) {
        this.user_id = user_id;
        this.content = content;
        this.profile = profile;
    }

    public static DateCommentBaseDto of(Long user_id, String content, Profile profile) {
        return new DateCommentBaseDto(user_id, content, profile);
    }
}
