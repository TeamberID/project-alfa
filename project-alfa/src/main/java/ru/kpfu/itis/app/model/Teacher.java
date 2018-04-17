package ru.kpfu.itis.app.model;

import lombok.*;

import javax.persistence.*;
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
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    private List<TeacherComment> comments;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "photo_id")
    private TeacherPhoto photo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id")
    )
    private List<Subject> subjects;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher_score_id")
    private TeacherScore teacherScore;
}
