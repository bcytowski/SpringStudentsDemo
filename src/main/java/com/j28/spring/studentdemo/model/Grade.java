package com.j28.spring.studentdemo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identyfikator jest generowany przez bazę
    private Long id;

    private double value;

    @Enumerated(value = EnumType.STRING)
    private GradeSubject subject;

    @CreationTimestamp
    private LocalDateTime dateAdded;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne (fetch = FetchType.LAZY)


    private Student student;

    public Grade(double value, GradeSubject subject) {
        this.value = value;
        this.subject = subject;
    }
}
