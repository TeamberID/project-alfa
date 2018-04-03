package ru.kpfu.itis.app.dto;

import lombok.*;
import ru.kpfu.itis.app.model.TeacherScore;

import java.util.List;
import java.util.stream.Collectors;

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
public class TeacherScoreDto {

    private Long id;

    private Double sum;

    private Long voteCount;

    private List<CriteriaScoreDto> criteriaScores;

    private TeacherScoreDto(TeacherScore teacherScore) {
        id = teacherScore.getId();
        sum = teacherScore.getSum();
        voteCount = teacherScore.getVoteCount();
        criteriaScores = teacherScore.getCriteriaScores().stream()
                .map(CriteriaScoreDto::createOnCriteriaScore)
                .collect(Collectors.toList());
    }

    public static TeacherScoreDto createOnTeacherScore(TeacherScore teacherScore) {
        return new TeacherScoreDto(teacherScore);
    }
}
