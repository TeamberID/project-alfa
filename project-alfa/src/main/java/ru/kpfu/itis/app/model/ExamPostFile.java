package ru.kpfu.itis.app.model;

import lombok.*;

import javax.persistence.*;

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
public class ExamPostFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_post_id")
    private ExamPost examPost;
}
