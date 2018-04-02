package ru.kpfu.itis.app.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.dto.TeacherDto;
import ru.kpfu.itis.app.model.Teacher;
import ru.kpfu.itis.app.repositories.TeachersRepository;
import ru.kpfu.itis.app.services.SessionService;
import ru.kpfu.itis.app.services.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class TeacherServiceImpl implements TeacherService {

    private SessionService sessionService;
    private TeachersRepository teacherRepository;

    public TeacherServiceImpl(SessionService sessionService, TeachersRepository teacherRepository) {
        this.sessionService = sessionService;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<TeacherDto> getUserTeachersDto(Authentication authentication) {
        return sessionService.getUserExams(authentication).stream()
                .map(exam -> TeacherDto.createOnTeacherAndSubject(exam.getTeacher(), exam.getSubject()))
                .collect(Collectors.toList());
    }

    @Override
    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findOne(teacherId);
    }
}
