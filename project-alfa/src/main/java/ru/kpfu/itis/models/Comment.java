package ru.kpfu.itis.models;

import lombok.*;

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
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "comment")
public class Comment {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private Date date;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "author_id")
   private User author;

   private String text;

}
