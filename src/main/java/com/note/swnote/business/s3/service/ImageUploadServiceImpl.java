package com.note.swnote.business.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.note.swnote.dto.request.s3.ImageFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageUploadServiceImpl implements ImageUploadService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String imageUpload(MultipartFile imgRequest) {

        ImageFile imageFile = ImageFile.from(imgRequest);


        MultipartFile file = imageFile.getMultipartFile();
        ObjectMetadata metadata = createObjectMetadata(file);
        try (InputStream inputStream = file.getInputStream()) {

            amazonS3.putObject(new PutObjectRequest(bucket, imageFile.getStoredFileName(), inputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

        } catch (IOException e) {
            throw new IllegalArgumentException("파일 업로드에 실패하였습니다.");
        }

        String imageUrl = amazonS3.getUrl(bucket, imageFile.getStoredFileName()).toString();
        log.info("imageUpload Success = {}", imageUrl);

        return imageUrl;
    }

    private ObjectMetadata createObjectMetadata(MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        return metadata;
    }
}
