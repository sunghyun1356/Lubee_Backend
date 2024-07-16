package com.Lubee.Lubee.memory.dto;

import com.Lubee.Lubee.enumset.Reaction;
import com.Lubee.Lubee.profile.domain.Profile;
import lombok.*;

@Data
@NoArgsConstructor
@ToString
@Getter
public class MemoryBaseDto {
    private String location_name;
    private String picture;
    private String writer_profile;
    private Reaction reaction1;
    private Reaction reaction2;

    public MemoryBaseDto(String location_name, String picture, Profile writer_profile, Reaction reaction1, Reaction reaction2)
    {
        this.location_name = location_name;
        this.picture = picture;
        this.writer_profile = writer_profile.getProfielUrl();
        this.reaction1 = reaction1;
        this.reaction2 = reaction2;

    }
    public static MemoryBaseDto of(String location_name, String picture, Profile writer_profile, Reaction reaction1, Reaction reaction2)
    {
        return new MemoryBaseDto(location_name, picture, writer_profile, reaction1, reaction2);
    }

}
