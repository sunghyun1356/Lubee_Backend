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

    public MemoryBaseDto(String location_name, String picture, Profile writer_profile, Reaction reaction1, Reaction reaction2)
    {
        this.location_name = location_name;
        this.picture = picture;
        this.writer_profile = writer_profile;
        this.reaction1 = reaction1;
        this.reaction2 = reaction2;

    }
    public static MemoryBaseDto of(String location_name, String picture, Profile writer_profile, Reaction reaction1, Reaction reaction2)
    {
        return new MemoryBaseDto(location_name, picture, writer_profile, reaction1, reaction2);
    }

}
