package ru.kpfu.itis.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Timur Iderisov on 22.04.2018.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "tutor")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String contacts;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tutors")
    private List<Subject> subjects;
}
