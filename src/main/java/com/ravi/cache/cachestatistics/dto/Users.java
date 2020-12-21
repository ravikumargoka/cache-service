package com.ravi.cache.cachestatistics.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Users implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5605748419820569559L;

    private List<User> users;

}
