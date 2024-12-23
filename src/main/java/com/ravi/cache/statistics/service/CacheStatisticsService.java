package com.ravi.cache.statistics.service;

import com.ravi.cache.statistics.dto.CacheData;
import com.ravi.cache.statistics.dto.CacheDetails;

public interface CacheStatisticsService {

    /**
     * Method to get the statistics of a given cache
     *
     * @param cacheAliasName
     * @param cacheKeyClass
     * @param cacheObjectClass
     * @return CacheData
     */
    CacheData getStatistics(String cacheAliasName, Class cacheKeyClass, Class cacheObjectClass);

    /**
     * Method to get the statistics of all the caches in the application
     *
     * @return CacheDetails
     */
    CacheDetails getAllCacheStatistics();
}
