package ru.kpfu.itis.app.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.CommentReport;
import ru.kpfu.itis.app.model.User;
import ru.kpfu.itis.app.repositories.CommentReportRepository;
import ru.kpfu.itis.app.services.AuthenticationService;
import ru.kpfu.itis.app.services.CommentReportService;

import java.util.Optional;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 22.04.2018
 */
@Service
public class CommentReportServiceImpl implements CommentReportService{

    private AuthenticationService authenticationService;
    private CommentReportRepository commentReportRepository;

    public CommentReportServiceImpl(AuthenticationService authenticationService,
                                    CommentReportRepository commentReportRepository) {
        this.authenticationService = authenticationService;
        this.commentReportRepository = commentReportRepository;
    }


    @Override
    public boolean hasUserReported(Authentication authentication, Long commentId) {
        User user = authenticationService.getUserByAuthentication(authentication).getUser();
        Optional<CommentReport> commentReportOptional = commentReportRepository.findByCommentIdAndUserId(commentId, user.getId());
        return commentReportOptional.isPresent();
    }

    @Override
    public void registerReport(Authentication authentication, Long commentId) {
        commentReportRepository.save(CommentReport.builder()
                .commentId(commentId)
                .userId(authenticationService.getUserByAuthentication(authentication).getId())
                .build());
    }
}
