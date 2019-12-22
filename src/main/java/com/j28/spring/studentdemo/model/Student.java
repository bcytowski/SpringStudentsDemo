package com.j28.spring.studentdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data // getter setter, tostring hashcode equals
@AllArgsConstructor
@NoArgsConstructor
public class Student  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identyfikator jest generowany przez bazę
    private Long id;
    private String firstName;
    private String lastName;
    private String indexNumber;
    private int age;
    private boolean suspended;

    // metoda equals i hashcode jest nadpisana ponieważ używamy adnotacji @Data która zawiera @EqualsHashCode.
    // z tego powodu hashcode generowany jest na podstawie WSZYSTKICH pól, w tym kolekcji GRADE. Żeby obliczyć
    // hashcode obiektu student, należy obliczyć hashcode wszystkich ocen. Wewnątrz oceny również mamy taką samą adnotację
    // co sprawia że żeby obliczyć hashcode studenta musimy obliczyć hashcode oceny i metoda się zapętla
    // rekurencyjnie aż do wystąpienia "Stack overflow"

    // to samo dzieje się z metodą tostring
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Grade> grades;

}
