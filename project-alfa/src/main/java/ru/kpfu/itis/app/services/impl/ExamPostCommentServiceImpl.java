package ru.kpfu.itis.app.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.dto.ExamPostCommentDto;
import ru.kpfu.itis.app.forms.ExamPostCommentAddingForm;
import ru.kpfu.itis.app.model.ExamPost;
import ru.kpfu.itis.app.model.ExamPostComment;
import ru.kpfu.itis.app.model.User;
import ru.kpfu.itis.app.repositories.ExamPostCommentRepository;
import ru.kpfu.itis.app.repositories.ExamPostsRepository;
import ru.kpfu.itis.app.services.AuthenticationService;
import ru.kpfu.itis.app.services.ExamPostCommentService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class ExamPostCommentServiceImpl implements ExamPostCommentService {

    private AuthenticationService authenticationService;
    private ExamPostCommentRepository examPostCommentRepository;
    private ExamPostsRepository examPostRepository;

    public ExamPostCommentServiceImpl(AuthenticationService authenticationService, ExamPostCommentRepository examPostCommentRepository, ExamPostsRepository examPostRepository) {
        this.authenticationService = authenticationService;
        this.examPostCommentRepository = examPostCommentRepository;
        this.examPostRepository = examPostRepository;
    }

    @Override
    public void addExamPostComment(Authentication authentication, ExamPostCommentAddingForm form) {
        User user = authenticationService.getUserByAuthentication(authentication).getUser();
        ExamPost examPost = examPostRepository.findOne(form.getExamPostId());
        ExamPostComment examPostComment = ExamPostComment.builder()
                .text(form.getText())
                .examPost(examPost)
                .author(user)
                .reports(0)
                .date(Date.valueOf(LocalDate.now()))
                .build();
        examPostCommentRepository.save(examPostComment);
    }

    @Override
    public List<ExamPostComment> getAllByExamPostId(Long examPostId) {
        return examPostCommentRepository.findAllByExamPostId(examPostId);
    }

    @Override
    public List<ExamPostCommentDto> getAllDtoByExamPostId(Long examPostId) {
        return getAllByExamPostId(examPostId).stream()
                .map(ExamPostCommentDto::createByExamPostComment)
                .collect(Collectors.toList());
    }
}
