package ru.kpfu.itis.app.services.impl;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.FileInfo;
import ru.kpfu.itis.app.repositories.FileInfoRepository;
import ru.kpfu.itis.app.services.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class FileServiceImpl implements FileService {

    private FileInfoRepository fileInfoRepository;

    public FileServiceImpl(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    @SneakyThrows
    @Override
    public void writeFileToResponse(Long fileInfoId, HttpServletResponse response) {
        FileInfo fileInfo = fileInfoRepository.findOne(fileInfoId);
        response.setContentType(fileInfo.getType());
        InputStream inputStream = new FileInputStream(new File(fileInfo.getPath()));
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }
}
