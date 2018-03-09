package ru.kpfu.itis.app.services;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface FileService {
    void writeFileToResponse(Long fileInfoId, HttpServletResponse response);
}
