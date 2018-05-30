package ru.kpfu.itis.app.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.Comment;
import ru.kpfu.itis.app.model.ExamPost;
import ru.kpfu.itis.app.services.*;

import java.util.Optional;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 22.04.2018
 */

@Service
public class ReportServiceImpl implements ReportService {

    private CommentsService commentsService;
    private ExamPostService examPostService;
    private CommentReportService commentReportService;
    private ExamPostReportService examPostReportService;

    public ReportServiceImpl(CommentsService commentsService,
                             ExamPostService examPostService,
                             CommentReportService commentReportService,
                             ExamPostReportService examPostReportService) {
        this.commentsService = commentsService;
        this.examPostService = examPostService;
        this.commentReportService = commentReportService;
        this.examPostReportService = examPostReportService;
    }

    @Override
    public String reportCommentResponse(Long id, Authentication authentication) {
        String response;
        Optional<Comment> commentOptional = Optional.ofNullable(commentsService.get(id));
        if (commentOptional.isPresent()) {
            if (!commentReportService.hasUserReported(authentication, id)) {
                commentsService.incReportsNumber(id);
                commentReportService.registerReport(authentication, id);
                response = "Жалоба успешно отправлена";
            } else {
                response = "Вы уже отправляли жалобу на этот пост";
            }
        } else {
            response = "Такого комментария не существует";
        }
        return response;
    }

    @Override
    public String reportPostResponse(Long id, Authentication authentication) {
        String response;
        Optional<ExamPost> examPostOptional = Optional.ofNullable(examPostService.getExamPost(id));
        if (examPostOptional.isPresent()) {
            if (!examPostReportService.hasUserReported(authentication, id)) {
                examPostService.incReportsNumber(id);
                examPostReportService.registerReport(authentication, id);
                response = "Жалоба успешно отправлена";
            } else {
                response = "Вы уже отправляли жалобу на этот пост";
            }
        } else {
            response = "Такого поста не существует";
        }
        return response;
    }
}
