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

    private FileInfo fileInfo;

    private Long examPostId;

    private ExamPostFileDto(ExamPostFile examPostFile) {
        id = examPostFile.getId();
        fileInfo = examPostFile.getFileInfo();
        examPostId = examPostFile.getExamPost().getId();
    }

    public static ExamPostFileDto createOnExamPostFile(ExamPostFile examPostFile) {
        return new ExamPostFileDto(examPostFile);
    }
}
