package ru.kpfu.itis.app.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManualAddingForm {
    @NotEmpty
    private String title;
    @NotEmpty
    private String author;
    @NotEmpty
    private String fileStorageName;
    @NotNull
    private Long examId;
}
