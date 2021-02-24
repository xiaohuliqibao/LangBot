package top.kagerou.lang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFileResponse {

    private String fileName;
    private String fileDownloadUrl;
    private String fileType;
    private String message;
    private long size;

    public UploadFileResponse(String message) {
        this.message = message;
    }
}