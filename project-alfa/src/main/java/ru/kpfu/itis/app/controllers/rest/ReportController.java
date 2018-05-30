package ru.kpfu.itis.app.controllers.rest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.app.services.ReportService;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 22.04.2018
 */
@RestController
@RequestMapping("api/report")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/comment/{id}")
    public String reportComment(@PathVariable("id") Long id,
                                Authentication authentication){
        return reportService.reportCommentResponse(id, authentication);
    }

    @PostMapping("/exam-post/{id}")
    public String reportPost(@PathVariable("id") Long id,
                           Authentication authentication){
        return reportService.reportPostResponse(id, authentication);
    }
}
