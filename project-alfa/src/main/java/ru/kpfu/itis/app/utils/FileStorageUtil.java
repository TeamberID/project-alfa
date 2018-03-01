package ru.kpfu.itis.app.utils;

import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.app.model.FileInfo;
import ru.kpfu.itis.app.model.Image;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
@Component
public class FileStorageUtil {

    @Value("${storage.path.doc}")
    private String docsStoragePath;

    public FileInfo getDocFileInfoByMultipart(MultipartFile file) {
        FileInfo fileInfo = convertFromMultipart(file);
        fileInfo.setPath(getFullPathOfDoc(fileInfo.getStorageFileName()));
        return fileInfo;
    }

    private String getFullPathOfDoc(String storageFileName) {
        return docsStoragePath + "/" + storageFileName;
    }

    private FileInfo convertFromMultipart(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        long size = file.getSize();
        String storageFileName = createStorageFileName(originalFileName);
        return FileInfo.builder()
                .originalFileName(originalFileName)
                .storageFileName(storageFileName)
                .size(size)
                .type(contentType)
                .build();
    }

    @SneakyThrows
    public void saveDocumentImageToStorage(MultipartFile file, Image documentImage) {
        Files.copy(file.getInputStream(), Paths.get(docsStoragePath, documentImage.getFileInfo().getStorageFileName()));
    }

    private String createStorageFileName(String originalFileName) {
        String extension = FilenameUtils.getExtension(originalFileName);
        String newFileName = UUID.randomUUID().toString();
        return newFileName + "." + extension;
    }

    public String getDocsStoragePath() {
        return docsStoragePath;
    }

    @SneakyThrows
    public void removeFileFromStorage(FileInfo fileInfo) {
        Files.delete(Paths.get(fileInfo.getPath()));
    }
}
