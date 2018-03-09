package ru.kpfu.itis.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.app.services.FileService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
@Controller
public class FileController {
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "/file/{file-info-id}", method = RequestMethod.GET)
    public void getFile(@PathVariable("file-info-id")Long fileInfoId, HttpServletResponse response) {
        fileService.writeFileToResponse(fileInfoId, response);
    }
}
