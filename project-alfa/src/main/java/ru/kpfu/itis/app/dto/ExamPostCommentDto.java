package ru.kpfu.itis.app.dto;

import lombok.*;
import ru.kpfu.itis.app.model.ExamPostComment;
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
public class ExamPostCommentDto {
    private Long id;

    private Date date;

    private User author;

    private String text;

    private Long examPostId;

    private ExamPostCommentDto(ExamPostComment examPostComment) {
        id = examPostComment.getId();
        date = examPostComment.getDate();
        author = examPostComment.getAuthor();
        text = examPostComment.getText();
        examPostId = examPostComment.getExamPost().getId();
    }

    public static ExamPostCommentDto createByExamPostComment(ExamPostComment examPostComment) {
        return new ExamPostCommentDto(examPostComment);
    }
}
