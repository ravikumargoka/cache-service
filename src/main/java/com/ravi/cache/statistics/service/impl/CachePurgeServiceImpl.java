package com.ravi.cache.statistics.service.impl;

import com.ravi.cache.statistics.service.CachePurgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.CacheManager;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CachePurgeServiceImpl implements CachePurgeService {

    private final CacheManager cacheManager;

    public CachePurgeServiceImpl(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ravi.cache.service.CachePurgeService#purgeCache(java.lang.String)
     */
    @Override
    public void purgeCache(String cacheAliasName) {
        if (log.isDebugEnabled()) {
            log.debug("START :: removing cache: {}", cacheAliasName);
        }
        Cache<String, Object> cache = cacheManager.getCache(cacheAliasName, String.class, Object.class);
        if (null != cache) {
            cache.removeAll();
        }
        if (log.isDebugEnabled()) {
            log.debug("START :: removing cache: {}", cacheAliasName);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see com.ravi.cache.service.CachePurgeService#purgeAllCache()
     */
    @Override
    public void purgeAllCache() {
        if (log.isDebugEnabled()) {
            log.debug("START :: removing all caches.");
        }
        Iterable<String> allCacheNames = cacheManager.getCacheNames();
        if (null != allCacheNames) {
            for (String cacheAliasName : allCacheNames) {
                log.info("The cache alias name: {}", cacheAliasName);
                Cache<String, Object> cache = cacheManager.getCache(cacheAliasName, String.class, Object.class);
                if (null != cache) {
                    cache.removeAll();
                }
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("END :: removing all caches.");
        }
    }

    /**
     * Method to get all cache alias names
     *
     * @return cacheAliases
     */
    @Override
    public List<String> getAllCacheAlias() {
        List<String> cacheAliases = new ArrayList<>();
        if (log.isDebugEnabled()) {
            log.debug("START :: Getting all cache alias names.");
        }
        Iterable<String> allCacheNames = cacheManager.getCacheNames();
        allCacheNames.forEach(cacheAliases::add);
        return cacheAliases;
    }
}
