package ru.kpfu.itis.app.services;


import ru.kpfu.itis.app.forms.TeacherAddingForm;
import org.springframework.security.core.Authentication;
import ru.kpfu.itis.app.dto.TeacherDto;
import ru.kpfu.itis.app.dto.TeacherScoreDto;
import ru.kpfu.itis.app.forms.TeacherVoteFrom;
import ru.kpfu.itis.app.model.Teacher;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface TeacherService {
    List<TeacherDto> getUserTeachersDto(Authentication authentication);

    Teacher getTeacherById(Long teacherId);
	
	List<Teacher> getAll();

    void add(TeacherAddingForm teacherAddingForm);

    void delete(Long id);

    void updateTeacherRating(TeacherVoteFrom teacherVoteFrom, Authentication authentication);

    TeacherScoreDto getTeacherScoreDtoByTeacherId(Long teacherId);

}
