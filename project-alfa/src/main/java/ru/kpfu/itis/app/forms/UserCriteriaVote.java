package ru.kpfu.itis.app.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCriteriaVote {
    @JsonProperty("criteriaId")
    private Long criteriaId;
    @JsonProperty("rate")
    private Byte rate;
}
