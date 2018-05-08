package ru.kpfu.itis.app.dto;

import lombok.*;
import ru.kpfu.itis.app.model.Subject;
import ru.kpfu.itis.app.model.Teacher;
import ru.kpfu.itis.app.model.TeacherComment;
import ru.kpfu.itis.app.model.TeacherPhoto;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class TeacherDto {

    private Long id;

    private String name;

    private List<TeacherComment> comments;

    private TeacherPhoto photo;

    private TeacherDto(Teacher teacher) {
        id = teacher.getId();
        name = teacher.getName();
        comments = teacher.getComments();
        photo = teacher.getPhoto();
    }

    public static TeacherDto createOnTeacher(Teacher teacher) {
        return new TeacherDto(teacher);
    }
}
