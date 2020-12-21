package com.ravi.cache.cachestatistics.service;

import com.ravi.cache.cachestatistics.dto.CacheData;
import com.ravi.cache.cachestatistics.dto.CacheDetails;

public interface CacheService {
    /**
     * Method to get the cache data of a give cache
     * @param cacheName
     * @param cacheKeyClass
     * @param cacheObjectClass
     * @return cacheData
     */
    CacheData getCacheData(String cacheName, Class cacheKeyClass, Class cacheObjectClass);

    /**
     * Method to get the statistics of all caches
     * @return cacheDetails
     */
    CacheDetails getAllCacheStatistics();
}
