package top.kagerou.lang.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import top.kagerou.lang.entity.FileInfo;
import top.kagerou.lang.entity.UploadFileResponse;
import top.kagerou.lang.service.VoiceStorageService;

@RestController
public class VoiceFileController {

    @Autowired
    private VoiceStorageService voiceStorageService;

    @GetMapping(value = "/test")
    public String getTest(@RequestParam("param") String param) {
        return param;
    }

    @PostMapping(value = "/voice/upload")
    public ResponseEntity<UploadFileResponse> uploadVoice(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            voiceStorageService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new UploadFileResponse(message));
        } catch (Exception e) {
            // TODO: handle exception
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new UploadFileResponse(message));
        }
    }

    @GetMapping(value = "/voice/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {

        List<FileInfo> fileInfos = voiceStorageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(VoiceFileController.class, "getFile", path.getFileName().toString()).build()
                    .toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/voice/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws UnsupportedEncodingException {
        Resource file = voiceStorageService.load(filename);

        HttpHeaders responHeaders = new HttpHeaders();
        // 处理中文乱码
        String disposition = "attachment; filename=" + URLEncoder.encode(file.getFilename(), "UTF-8");
        responHeaders.set("Content-Disposition", disposition);

        return ResponseEntity.ok().headers(responHeaders).body(file);
    }

}