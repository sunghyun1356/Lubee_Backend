package com.Lubee.Lubee.upload.service;

import com.Lubee.Lubee.memory.domain.Memory;
import com.Lubee.Lubee.memory.repository.MemoryRepository;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadService {

    private final AmazonS3Client amazonS3Client;
    private final MemoryRepository memoryRepository;

    @Value("${S3_BUCKET_NAME}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

//    public ResponseEntity<String> uploadProfile(MultipartFile file)
//    {
//        try{
//            String fileName = file.getOriginalFilename();
//            String folder = "/profile"; // 저장할 폴더
//            String fileUrl = "https://" + bucket + ".s3." + region + ".amazonaws.com" + folder + "/" + fileName;
//            ObjectMetadata metadata= new ObjectMetadata();
//            metadata.setContentType(file.getContentType());
//            metadata.setContentLength(file.getSize());
//            amazonS3Client.putObject(bucket + folder, fileName, file.getInputStream(), metadata);
//
//            Profile profile = new Profile();
//            profile.setProfielUrl(fileUrl);
//
//            profileRepository.save(profile);
//
//            return ResponseEntity.ok(fileUrl);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

//    public ResponseEntity<String> uploadPicture(MultipartFile file, String pictureName)
//    {
//        String fileName =file.getOriginalFilename();
//        String folder = "/skillset"; // 저장할 폴더
//
//        String fileUrl= "https://" + bucket + ".s3." + region + ".amazonaws.com" + folder + "/" + fileName;
//        ObjectMetadata metadata= new ObjectMetadata();
//        metadata.setContentType(file.getContentType());
//        metadata.setContentLength(file.getSize());
//        amazonS3Client.putObject(bucket + folder, fileName, file.getInputStream(), metadata);
//
//        Memory memory = new Memory();
//        memory.setPicture(fileUrl);
//        memoryRepository.save(memory);
//
//        return ResponseEntity.ok(fileUrl);
//    } catch (IOException e) {
//        e.printStackTrace();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }

}
