package ru.kpfu.itis.app.services;

<<<<<<< HEAD
import ru.kpfu.itis.app.forms.TeacherAddingForm;
=======
import org.springframework.security.core.Authentication;
import ru.kpfu.itis.app.dto.TeacherDto;
import ru.kpfu.itis.app.dto.TeacherScoreDto;
import ru.kpfu.itis.app.forms.TeacherVoteFrom;
>>>>>>> d8b2b29360bfeab196a45bc7f089ab37da3aae70
import ru.kpfu.itis.app.model.Teacher;

import java.util.List;

/**
<<<<<<< HEAD
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 24.03.2018
 */
public interface TeacherService {
    List<Teacher> getAll();

    void add(TeacherAddingForm teacherAddingForm);

    void delete(Long id);
=======
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface TeacherService {
    List<TeacherDto> getUserTeachersDto(Authentication authentication);

    Teacher getTeacherById(Long teacherId);

    void updateTeacherRating(TeacherVoteFrom teacherVoteFrom, Authentication authentication);

    TeacherScoreDto getTeacherScoreDtoByTeacherId(Long teacherId);
>>>>>>> d8b2b29360bfeab196a45bc7f089ab37da3aae70
}
