package com.Lubee.Lubee.memory.dto;

import com.Lubee.Lubee.date_comment.dto.DateCommentBaseDto;
import com.Lubee.Lubee.enumset.Profile;
import com.Lubee.Lubee.memory.domain.Memory;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
public class HomeDto {

    private Date today;
    private Long love_day;
    private int honey;
    private List<Profile> profiles;
    private List<DateCommentBaseDto> dateCommentBasetDtoList;
    private List<MemoryBaseDto> memoryBaseDtoList;


}
