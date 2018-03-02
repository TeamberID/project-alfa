package ru.kpfu.itis.models;

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
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "session")
    private List<Exam> exams;

    private byte semesterNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="institute_id")
    private Institute institute;
}
