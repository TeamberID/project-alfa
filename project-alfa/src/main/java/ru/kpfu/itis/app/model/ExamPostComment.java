package ru.kpfu.itis.app.model;

import lombok.*;
import ru.kpfu.itis.app.model.status.CommentStatus;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("exam-post")
public class ExamPostComment extends Comment {

    @Builder
    private ExamPostComment(Long id, Date date, User author, String text, ExamPost examPost, Integer reports) {
        super(id, date, author, reports, CommentStatus.POSTED, text);
        this.examPost = examPost;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_post_id")
    private ExamPost examPost;
}
