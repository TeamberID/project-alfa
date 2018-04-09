package ru.kpfu.itis.app.dto;

import lombok.*;
import ru.kpfu.itis.app.model.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

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
public class ExamPostDto {
    private Long id;

    private Date date;

    private User author;

    private String text;

    private Long examId;

    private int commentsCount;

    private int attachmentsCount;

    private List<ExamPostCommentDto> comments;

    private List<ExamPostFileDto> attachments;

    private ExamPostDto(ExamPost examPost) {
        id = examPost.getId();
        date = examPost.getDate();
        author = examPost.getAuthor();
        text = examPost.getText();
        examId = examPost.getId();
        comments = examPost.getComments().stream()
                .map(ExamPostCommentDto::createByExamPostComment)
                .collect(Collectors.toList());
        attachments = examPost.getAttachments().stream()
                .map(ExamPostFileDto::createOnExamPostFile)
                .collect(Collectors.toList());
        commentsCount = comments.size();
        attachmentsCount = attachments.size();
    }

    public static ExamPostDto createByExamPost(ExamPost examPost) {
        return new ExamPostDto(examPost);
    }
}
