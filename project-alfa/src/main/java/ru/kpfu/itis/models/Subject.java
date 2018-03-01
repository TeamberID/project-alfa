package ru.kpfu.itis.models;

import lombok.*;

import javax.persistence.*;

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
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
