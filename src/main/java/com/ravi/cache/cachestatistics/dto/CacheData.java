package com.ravi.cache.cachestatistics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CacheData implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 674669199409003727L;

    private String name;
    private boolean enabled;
    @JsonProperty("statistics")
    private CacheStatistics cacheStatistics;

}

