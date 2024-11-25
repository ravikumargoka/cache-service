package com.ravi.cache.statistics.service;

import java.util.List;

public interface CachePurgeService {

    /**
     * Method to purge/remove a particular cache
     *
     * @param cacheAliasName
     */
    void purgeCache(String cacheAliasName);

    /**
     * Method to purge/remove all caches of the application
     */
    void purgeAllCache();

    List<String> getAllCacheAlias();
}
