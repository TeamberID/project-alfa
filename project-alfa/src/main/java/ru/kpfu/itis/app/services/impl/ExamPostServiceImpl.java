package ru.kpfu.itis.app.services.impl;

import com.google.common.collect.Lists;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.app.dto.ExamPostDto;
import ru.kpfu.itis.app.forms.ExamPostAddingForm;
import ru.kpfu.itis.app.model.*;
import ru.kpfu.itis.app.repositories.ExamPostsRepository;
import ru.kpfu.itis.app.repositories.ExamsRepository;
import ru.kpfu.itis.app.services.AuthenticationService;
import ru.kpfu.itis.app.services.ExamPostService;
import ru.kpfu.itis.app.utils.FileStorageUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class ExamPostServiceImpl implements ExamPostService {
    private AuthenticationService authenticationService;
    private ExamPostsRepository examPostRepository;
    private ExamsRepository examRepository;
    private FileStorageUtil fileStorageUtil;

    public ExamPostServiceImpl(AuthenticationService authenticationService, ExamPostsRepository examPostRepository, ExamsRepository examRepository, FileStorageUtil fileStorageUtil) {
        this.authenticationService = authenticationService;
        this.examPostRepository = examPostRepository;
        this.examRepository = examRepository;
        this.fileStorageUtil = fileStorageUtil;
    }

    @Override
    public void addExamPost(Authentication authentication, ExamPostAddingForm form) {
        User user = authenticationService.getUserByAuthentication(authentication).getUser();
        Exam exam = examRepository.findOne(form.getExamId());
        ExamPost examPost = ExamPost.builder()
                .date(Date.valueOf(LocalDate.now()))
                .author(user)
                .text(form.getText())
                .exam(exam)
                .comments(Collections.emptyList())
                .build();
        List<ExamPostFile> attachments = createExamPostAttachments(form.getFiles(), examPost);
        examPost.setAttachments(attachments);
        examPostRepository.save(examPost);
    }

    private List<ExamPostFile> createExamPostAttachments(MultipartFile[] files, ExamPost examPost) {
        List<ExamPostFile> attachments = Lists.newArrayList();
        if (files != null) {
            for (MultipartFile file: files) {
                FileInfo fileInfo = fileStorageUtil.getExamPostInfoByMultipart(file);
                if (fileInfo.getSize() != 0) {
                    ExamPostFile examPostFile = ExamPostFile.builder().fileInfo(fileInfo).examPost(examPost).build();
                    attachments.add(examPostFile);
                    fileStorageUtil.saveExamPostFileToStorage(file, examPostFile);
                }
            }
        }
        return attachments;
    }


    @Override
    public List<ExamPost> getAllByExamId(Long examId) {
        return examPostRepository.findByExamId(examId);
    }

    @Override
    public List<ExamPostDto> getAllDtoByExamId(Long examId) {
        return getAllByExamId(examId).stream()
                .map(ExamPostDto::createByExamPost)
                .collect(Collectors.toList());
    }

    @Override
    public ExamPost getExamPost(Long examPostId) {
        return examPostRepository.findOne(examPostId);
    }
}
