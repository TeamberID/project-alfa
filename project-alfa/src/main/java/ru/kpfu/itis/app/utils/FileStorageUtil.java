package ru.kpfu.itis.app.utils;

import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.app.model.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
@Component
public class FileStorageUtil {

    private AmazonClient amazonClient;

    @Value("${storage.path.doc}")
    private String docsStoragePath;

    @Value("${storage.path.doc}")
    private String examPostFilesStoragePath;

    @Value("${storage.path.doc}")
    private String teacherPhotoStoragePath;

    @Value("${storage.path.doc}")
    private String manualStoragePath;

    public FileStorageUtil(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    public FileInfo getDocFileInfoByMultipart(MultipartFile file) {
        FileInfo fileInfo = convertFromMultipart(file);
        fileInfo.setPath(getFullPathOfDoc(fileInfo.getStorageFileName()));
        return fileInfo;
    }

    public FileInfo getTeacherPhotoByMultipart(MultipartFile file) {
        FileInfo fileInfo = convertFromMultipart(file);
        fileInfo.setPath(getFullPathOfTeacherPhoto(fileInfo.getStorageFileName()));
        return fileInfo;
    }

    public FileInfo getManualFileInfoByMultipart(MultipartFile file) {
        FileInfo fileInfo = convertFromMultipart(file);
        fileInfo.setPath(getFullPathOfManual(fileInfo.getStorageFileName()));
        return fileInfo;
    }

    public FileInfo getExamPostInfoByMultipart(MultipartFile file) {
        FileInfo fileInfo = convertFromMultipart(file);
        fileInfo.setPath(getFullPathOfExamPost(fileInfo.getStorageFileName()));
        return fileInfo;
    }

    private String getFullPathOfManual(String storageFileName) {
        return manualStoragePath + "/" + storageFileName;
    }

    private String getFullPathOfTeacherPhoto(String storageName) {
        return teacherPhotoStoragePath + "/" + storageName;
    }

    private String getFullPathOfDoc(String storageFileName) {
        return docsStoragePath + "/" + storageFileName;
    }

    private String getFullPathOfExamPost(String storageFileName) {
        return examPostFilesStoragePath + "/" + storageFileName;
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
        String fileUrl = amazonClient.saveFileToStorage(file, documentImage.getFileInfo().getStorageFileName());
        documentImage.getFileInfo().setUrl(fileUrl);
    }

    @SneakyThrows
    public void saveExamPostFileToStorage(MultipartFile file, ExamPostFile examPostFile) {
        String fileUrl = amazonClient.saveFileToStorage(file, examPostFile.getFileInfo().getStorageFileName());
        examPostFile.getFileInfo().setUrl(fileUrl);
    }

    @SneakyThrows
    public void saveTeacherPhotoToStorage(MultipartFile file, TeacherPhoto teacherPhoto) {
        String fileUrl = amazonClient.saveFileToStorage(file, teacherPhoto.getFileInfo().getStorageFileName());
        teacherPhoto.getFileInfo().setUrl(fileUrl);
    }

    @SneakyThrows
    public void saveManualToStorage(MultipartFile file, Manual manual) {
        String fileUrl = amazonClient.saveFileToStorage(file, manual.getFileInfo().getStorageFileName());
        manual.getFileInfo().setUrl(fileUrl);
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

    public String getBucketURL() {
        return amazonClient.getEndpointUrl() + "/" + amazonClient.getBucketName();
    }
}
