package ru.kpfu.itis.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
@Entity
public class TeacherScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double sum;

    private Long voteCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacherScore", cascade = CascadeType.PERSIST)
    private List<CriteriaScore> criteriaScores;

    public Double getAverage() {
        return sum / voteCount;
    }
}
