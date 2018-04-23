package ru.kpfu.itis.app.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.CommentReport;
import ru.kpfu.itis.app.model.ExamPostReport;
import ru.kpfu.itis.app.model.User;
import ru.kpfu.itis.app.repositories.ExamPostReportRepository;
import ru.kpfu.itis.app.services.AuthenticationService;
import ru.kpfu.itis.app.services.CommentReportService;
import ru.kpfu.itis.app.services.ExamPostReportService;
import ru.kpfu.itis.app.services.ExamPostService;

import java.util.Optional;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 22.04.2018
 */
@Service
public class ExamPostReportServiceImpl implements ExamPostReportService{

    private AuthenticationService authenticationService;
    private ExamPostReportRepository examPostReportRepository;

    public ExamPostReportServiceImpl(AuthenticationService authenticationService, ExamPostReportRepository examPostReportRepository) {
        this.authenticationService = authenticationService;
        this.examPostReportRepository = examPostReportRepository;
    }

    @Override
    public boolean hasUserReported(Authentication authentication, Long postId) {
        User user = authenticationService.getUserByAuthentication(authentication).getUser();
        Optional<ExamPostReport> commentReportOptional = examPostReportRepository.findByPostIdAndUserId(postId, user.getId());
        return commentReportOptional.isPresent();
    }

    @Override
    public void registerReport(Authentication authentication, Long postId) {
        examPostReportRepository.save(ExamPostReport.builder()
                .postId(postId)
                .userId(authenticationService.getUserByAuthentication(authentication).getId())
                .build());
    }
}
