package com.ravi.cache.cachestatistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements Serializable {

    private static final long serialVersionUID = -746714052215319356L;

    private int id;
    private String subject;
    private String grade;
    private String firstName;
    private String lastName;
}
