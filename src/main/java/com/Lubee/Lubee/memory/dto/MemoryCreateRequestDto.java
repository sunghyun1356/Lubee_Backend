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
    Long location_id;
    MultipartFile picture;
}
