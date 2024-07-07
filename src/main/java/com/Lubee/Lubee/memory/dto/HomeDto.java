package com.Lubee.Lubee.memory.dto;

import com.Lubee.Lubee.enumset.Profile;
import com.Lubee.Lubee.memory.domain.Memory;
import lombok.*;

import java.util.Date;

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
    private Profile profile1;
    private Profile profile2;
    private DateCommentDto dateCommentDto;
    private List<MemoryBaseDto> memoryBaseDtoList;


}
