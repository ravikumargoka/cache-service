package com.ravi.cache.statistics.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TEACHERS")
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "grade")
    private String grade;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false, length = 50)
    private String email;


}
