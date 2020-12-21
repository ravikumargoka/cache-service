package com.ravi.cache.cachestatistics.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.io.Serializable;

@JsonPropertyOrder({ "averageGetTime", "averagePutTime", "averageRemoveTime", "cacheEvictions", "cacheGets",
        "cacheHitPercentage", "cacheHits", "cacheMisses", "cacheMissPercentage", "cachePuts", "cacheRemovals" })
@JsonRootName("statistics")
@Data
public class CacheStatistics implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2127894752626293966L;

    private float averageGetTime;
    private float averagePutTime;
    private float averageRemoveTime;
    private long cacheEvictions;
    private long cacheGets;
    private float cacheHitPercentage;
    private long cacheHits;
    private long cacheMisses;
    private float cacheMissPercentage;
    private long cachePuts;
    private long cacheRemovals;
}
