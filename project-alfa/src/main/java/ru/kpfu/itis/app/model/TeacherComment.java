package ru.kpfu.itis.app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@DiscriminatorValue("teacher")
public class TeacherComment extends Comment {

    @Builder
    private TeacherComment(Long id, Date date, User author, String text, Teacher teacher) {
        super(id, date, author,0, CommentStatus.POSTED, text);
        this.teacher = teacher;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
