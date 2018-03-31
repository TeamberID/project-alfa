package ru.kpfu.itis.app.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 01.03.2018
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "exam_post")
public class ExamPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(length = 2000)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "examPost")
    private List<ExamPostComment> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "examPost", cascade = CascadeType.PERSIST)
    private List<ExamPostFile> attachments;
}
