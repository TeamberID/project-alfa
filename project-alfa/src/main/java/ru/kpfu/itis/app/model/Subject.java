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
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "subject_tutor",
            joinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tutor_id", referencedColumnName = "id")
    )
    private List<Tutor> tutors;
}
