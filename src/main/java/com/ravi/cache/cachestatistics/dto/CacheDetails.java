package com.ravi.cache.cachestatistics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CacheDetails implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1002366696974949398L;

    @JsonProperty("caches")
    private List<CacheData> cacheData;

}
