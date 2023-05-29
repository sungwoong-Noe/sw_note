package com.note.swnote.business.s3.service;


import com.note.swnote.dto.request.s3.ImageFile;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {

    String imageUpload(MultipartFile imgRequest);

}
