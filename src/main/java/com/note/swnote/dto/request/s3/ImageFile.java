package com.note.swnote.dto.request.s3;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter @Setter
public class ImageFile {

    private String originalFileName;
    private String storedFileName;
    private MultipartFile multipartFile;

    public ImageFile(String originalFileName, String storedFileName, MultipartFile multipartFile) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.multipartFile = multipartFile;
    }

    public static ImageFile from(MultipartFile multipartFile) {
        String originalName = multipartFile.getOriginalFilename();
        return new ImageFile(originalName, createStoredFileName(originalName), multipartFile);
    }


    private static String createStoredFileName(String originalFileName) {
        var ext = extractExt(originalFileName);
        var uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private static String extractExt(String originalFileName) {
        var pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }

}
