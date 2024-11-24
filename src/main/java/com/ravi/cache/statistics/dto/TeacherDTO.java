package com.ravi.cache.statistics.dto;

import lombok.Data;

@Data
public class TeacherDTO {
    private Long id;
    private String subject;
    private String grade;
    private String firstName;
    private String lastName;
    private String email;
}