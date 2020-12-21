package com.ravi.cache.cachestatistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -414900542027962124L;

    private int userId;
    private String firstName;
    private String lastName;


}

