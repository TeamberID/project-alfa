package ru.kpfu.itis.app.model;

import lombok.*;

import javax.persistence.*;

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
public class CriteriaScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sum;

    private Long voteCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_score_id")
    private TeacherScore teacherScore;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criteria_id")
    private Criteria criteria;

    public Double getAverage() {
        return (double)(sum/voteCount);
    }
}
