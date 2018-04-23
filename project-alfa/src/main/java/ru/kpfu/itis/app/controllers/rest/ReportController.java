package ru.kpfu.itis.app.controllers.rest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.app.model.Comment;
import ru.kpfu.itis.app.model.ExamPost;
import ru.kpfu.itis.app.repositories.CommentsRepository;
import ru.kpfu.itis.app.repositories.ExamPostsRepository;
import ru.kpfu.itis.app.services.*;

import javax.security.auth.message.AuthException;
import java.util.Optional;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 22.04.2018
 */
@RestController
@RequestMapping("api/report")
public class ReportController {
    public static int NUMBER_OF_REPORTS_TO_DELETE = 10;
    private CommentsService commentsService;
    private ExamPostService examPostService;
    private CommentReportService commentReportService;
    private ExamPostReportService examPostReportService;

    public ReportController(CommentsService commentsService,
                            ExamPostService examPostService,
                            CommentReportService commentReportService,
                            ExamPostReportService examPostReportService) {
        this.commentsService = commentsService;
        this.examPostService = examPostService;
        this.commentReportService = commentReportService;
        this.examPostReportService = examPostReportService;
    }

    @PostMapping("/comment/{id}")
    public String reportComment(@PathVariable("id") Long id,
                                Authentication authentication){
        String response;
        Optional<Comment> commentOptional = Optional.ofNullable(commentsService.get(id));
        if(commentOptional.isPresent()) {
            if (!commentReportService.hasUserReported(authentication, id)) {
                commentsService.incReportsNumber(id);
                commentReportService.registerReport(authentication, id);
                response = "Жалоба успешно отправлена";
            } else {
                response = "Вы уже отправляли жалобу на этот пост";
            }
        }else{
            response = "Такого комментария не существует";
        }
        return response;
    }

    @PostMapping("/exam-post/{id}")
    public String reportPost(@PathVariable("id") Long id,
                           Authentication authentication){
        String response;
        Optional<ExamPost> examPostOptional = Optional.ofNullable(examPostService.getExamPost(id));
        if(examPostOptional.isPresent()) {
            if (!examPostReportService.hasUserReported(authentication, id)) {
                examPostService.incReportsNumber(id);
                examPostReportService.registerReport(authentication, id);
                response = "Жалоба успешно отправлена";
            } else {
                response = "Вы уже отправляли жалобу на этот пост";
            }
        }else{
            response = "Такого поста не существует";
        }
        return response;
    }
}
