package com.Lubee.Lubee.memory.dto;

import com.Lubee.Lubee.enumset.Profile;
import com.Lubee.Lubee.enumset.Reaction;
import lombok.*;

@Data
@NoArgsConstructor
@ToString
@Getter
public class MemoryBaseDto {

    private Long memory_id;
    private Long user_id;
    private String location_name;
    private String picture;
    private Profile writer_profile;
    private Reaction reaction1;
    private Reaction reaction2;
    private String upload_time;

    public MemoryBaseDto(Long memory_id, Long user_id, String location_name, String picture, Profile writer_profile, Reaction reaction1, Reaction reaction2, String upload_time)
    {
        this.memory_id = memory_id;
        this.user_id =user_id;
        this.location_name = location_name;
        this.picture = picture;
        this.writer_profile = writer_profile;
        this.reaction1 = reaction1;
        this.reaction2 = reaction2;
        this.upload_time = upload_time;
    }
    public static MemoryBaseDto of(Long memory_id, Long user_id, String location_name, String picture, Profile writer_profile, Reaction reaction1, Reaction reaction2, String upload_time)
    {
        return new MemoryBaseDto(memory_id, user_id, location_name, picture, writer_profile, reaction1, reaction2, upload_time);
    }

}
