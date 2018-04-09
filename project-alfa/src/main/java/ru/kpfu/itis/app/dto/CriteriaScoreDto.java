package ru.kpfu.itis.app.dto;

import lombok.*;
import ru.kpfu.itis.app.model.CriteriaScore;

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
public class CriteriaScoreDto {

    private Long id;

    private Long sum;

    private Long voteCount;

    private Long teacherScoreId;

    private Long criteriaId;

    private CriteriaScoreDto(CriteriaScore criteriaScore) {
        id = criteriaScore.getId();
        sum = criteriaScore.getSum();
        voteCount = criteriaScore.getVoteCount();
        teacherScoreId = criteriaScore.getTeacherScore().getId();
        criteriaId = criteriaScore.getCriteria().getId();
    }

    public static CriteriaScoreDto createOnCriteriaScore(CriteriaScore criteriaScore) {
        return new CriteriaScoreDto(criteriaScore);
    }
}
