package top.kagerou.lang.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface VoiceStorageService {
    public void init();

    public void save(MultipartFile file);

    public void delectAll();

    public Resource load(String filename);

    public Stream<Path> loadAll();
}