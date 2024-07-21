package com.Lubee.Lubee.memory.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
public class MemoryCreateRequestDto {
    String content;
    Long location_id;
    MultipartFile picture;
    Long couple_id;
}
