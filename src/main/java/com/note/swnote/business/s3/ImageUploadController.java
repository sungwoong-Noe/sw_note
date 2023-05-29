package com.note.swnote.business.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.note.swnote.business.s3.service.ImageUploadService;
import com.note.swnote.dto.request.s3.ImageFile;
import com.note.swnote.dto.request.s3.UploadImageRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class ImageUploadController {

    private final ImageUploadService imageUploadService;


    @PostMapping("/image")
    public String uploadImage(@ModelAttribute UploadImageRequest img, HttpServletRequest request) {

        String url = imageUploadService.imageUpload(img.getImg());

        return url;
    }


}
