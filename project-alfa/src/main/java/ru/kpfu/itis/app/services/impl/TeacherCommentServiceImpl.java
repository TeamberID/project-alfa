package ru.kpfu.itis.app.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.dto.TeacherCommentDto;
import ru.kpfu.itis.app.forms.TeacherCommentAddForm;
import ru.kpfu.itis.app.model.Teacher;
import ru.kpfu.itis.app.model.TeacherComment;
import ru.kpfu.itis.app.model.User;
import ru.kpfu.itis.app.repositories.TeacherCommentRepository;
import ru.kpfu.itis.app.repositories.TeachersRepository;
import ru.kpfu.itis.app.services.AuthenticationService;
import ru.kpfu.itis.app.services.TeacherCommentService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class TeacherCommentServiceImpl implements TeacherCommentService {

    private TeacherCommentRepository teacherCommentRepository;
    private TeachersRepository teacherRepository;
    private AuthenticationService authenticationService;

    public TeacherCommentServiceImpl(TeacherCommentRepository teacherCommentRepository, TeachersRepository teacherRepository, AuthenticationService authenticationService) {
        this.teacherCommentRepository = teacherCommentRepository;
        this.teacherRepository = teacherRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public void addTeacherComment(TeacherCommentAddForm teacherCommentAddForm, Authentication authentication) {
        User user = authenticationService.getUserByAuthentication(authentication).getUser();
        Teacher teacher = teacherRepository.findOne(teacherCommentAddForm.getTeacherId());
        TeacherComment teacherComment = TeacherComment.builder()
                .author(user)
                .teacher(teacher)
                .text(teacherCommentAddForm.getText())
                .date(Date.valueOf(LocalDate.now()))
                .build();
        teacherCommentRepository.save(teacherComment);
    }

    @Override
    public List<TeacherCommentDto> getAllTeacherCommentDtoByTeacherId(Long teacherId) {
        return teacherCommentRepository.findByTeacherId(teacherId).stream()
                .map(TeacherCommentDto::createOnTeacherComment)
                .collect(Collectors.toList());
    }
}
