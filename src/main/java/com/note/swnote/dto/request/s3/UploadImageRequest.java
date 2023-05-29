package com.note.swnote.dto.request.s3;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@NoArgsConstructor
public class UploadImageRequest {
    private MultipartFile img;

    public UploadImageRequest(MultipartFile img) {
        this.img = img;
    }
}
