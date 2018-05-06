package ru.kpfu.itis.app.dto;

import lombok.*;
import ru.kpfu.itis.app.model.ExamPostFile;
import ru.kpfu.itis.app.model.FileInfo;

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
public class ExamPostFileDto {
    private Long id;

    private String url;

    private Long examPostId;

    private ExamPostFileDto(ExamPostFile examPostFile) {
        id = examPostFile.getId();
        url = examPostFile.getUrl();
        examPostId = examPostFile.getExamPost().getId();
    }

    public static ExamPostFileDto createOnExamPostFile(ExamPostFile examPostFile) {
        return new ExamPostFileDto(examPostFile);
    }
}
