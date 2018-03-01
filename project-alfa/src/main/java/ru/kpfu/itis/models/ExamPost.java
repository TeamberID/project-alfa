package ru.kpfu.itis.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
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
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "exam_posts")
public class ExamPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private User author;
    private String text;
    private List<File> attachments;
}
