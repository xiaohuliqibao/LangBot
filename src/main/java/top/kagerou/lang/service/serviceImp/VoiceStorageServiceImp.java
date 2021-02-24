package top.kagerou.lang.service.serviceImp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import top.kagerou.lang.service.VoiceStorageService;
import top.kagerou.lang.util.VoicdFromateUtil;

@Slf4j
@Service
public class VoiceStorageServiceImp implements VoiceStorageService {

    private static String VOICE_CUSTOMIZE_MP3_PATH_LINUX = "/home/qibao/file/miraibot/voices/customize/mp3/";
    private final Path root = Paths.get(VOICE_CUSTOMIZE_MP3_PATH_LINUX);

    @Override
    public void delectAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public Stream<Path> loadAll() {
        // return null;
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (Exception e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            log.info(file.getOriginalFilename().toString());
            String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().length() - 4);
            if (VoicdFromateUtil.mp3ToAmr(fileName)) {
                log.info("转码成功");
            } else {
                log.error("转码失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

}
