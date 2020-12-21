package com.ravi.cache.cachestatistics.service;

public interface CachePurgeService {

    /**
     * Method to purge/remove a particular cache
     * @param cacheAliasName
     */
    void purgeCache(String cacheAliasName);

    /**
     * Method to purge/remove all caches of the application
     */
    void purgeAllCache();
}
