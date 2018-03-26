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
@Table(name = "university")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "university")
    private List<Institute> institutes;
}
