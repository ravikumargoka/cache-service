package com.ravi.cache.cachestatistics.entity;

import lombok.Data;

import javax.persistence.*;

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
