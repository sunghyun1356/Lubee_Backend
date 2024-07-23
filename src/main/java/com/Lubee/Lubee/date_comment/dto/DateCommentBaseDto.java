package com.Lubee.Lubee.date_comment.dto;

import com.Lubee.Lubee.date_comment.domain.DateComment;
import com.Lubee.Lubee.enumset.Profile;
import com.Lubee.Lubee.user.domain.User;
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

    public static DateCommentBaseDto from(DateComment dateComment, User user) {
        if (dateComment == null) {
            return null;
        }
        return new DateCommentBaseDto(user.getId(), dateComment.getContent(), user.getProfile());
    }
}
