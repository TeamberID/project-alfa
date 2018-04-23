package ru.kpfu.itis.app.model;

import lombok.*;
import ru.kpfu.itis.app.model.status.CommentStatus;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 01.03.2018
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Entity
@Inheritance
@DiscriminatorColumn(name = "type")
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    private Integer reports;

    @Enumerated(EnumType.STRING)
    private CommentStatus status;

    @Column(length = 300)
    private String text;

}
