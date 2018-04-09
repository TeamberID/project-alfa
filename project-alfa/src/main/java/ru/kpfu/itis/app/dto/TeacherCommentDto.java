package ru.kpfu.itis.app.dto;

import lombok.*;
import ru.kpfu.itis.app.model.TeacherComment;
import ru.kpfu.itis.app.model.User;

import java.sql.Date;

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
public class TeacherCommentDto {

    private Long id;

    private Date date;

    private User author;

    private String text;

    private Long teacherId;

    private TeacherCommentDto(TeacherComment teacherComment) {
        id = teacherComment.getId();
        date = teacherComment.getDate();
        author = teacherComment.getAuthor();
        text = teacherComment.getText();
        teacherId = teacherComment.getTeacher().getId();
    }

    public static TeacherCommentDto createOnTeacherComment(TeacherComment teacherComment) {
        return new TeacherCommentDto(teacherComment);
    }
}
