package ru.kpfu.itis.app.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.app.dto.TeacherDto;
import ru.kpfu.itis.app.model.Teacher;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface TeacherService {
    List<TeacherDto> getUserTeachersDto(Authentication authentication);

    Teacher getTeacherById(Long teacherId);
}
