package ru.kpfu.itis.app.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Timur Iderisov on 02.04.2018.
 */
@Controller
public class MyErrorController implements ErrorController {

    private static final String ERROR_MAPPING = "/error";

    @RequestMapping(value = ERROR_MAPPING)
    @Override
    public String getErrorPath() {
        return "access-denied";
    }
}