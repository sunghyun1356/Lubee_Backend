package com.Lubee.Lubee.memory.dto;

import com.Lubee.Lubee.enumset.Profile;
import com.Lubee.Lubee.enumset.Reaction;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
public class MemoryBaseDto {
    private String location_name;
    private String picture;
    private Profile writer_profile;
    private Reaction reaction1;
    private Reaction reaction2;
}
