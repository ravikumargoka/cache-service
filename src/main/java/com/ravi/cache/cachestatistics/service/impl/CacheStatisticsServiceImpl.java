package com.ravi.cache.cachestatistics.service.impl;

import com.ravi.cache.cachestatistics.dto.CacheData;
import com.ravi.cache.cachestatistics.dto.CacheDetails;
import com.ravi.cache.cachestatistics.service.CacheStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.CacheManager;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CacheStatisticsServiceImpl extends BaseCacheStatisticsServiceImpl implements CacheStatisticsService {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public CacheData getStatistics(String cacheAliasName, Class cacheKeyClass, Class cacheObjectClass) {
        if(log.isDebugEnabled()) {
            log.debug("START :: Getting the statistics for cache: {}, key class: {} and cache object class: {}", cacheAliasName, cacheKeyClass, cacheObjectClass);
        }
        CacheData cacheData = new CacheData();
        cacheData.setName(cacheAliasName);
        Cache<String, Object> usersCache = cacheManager.getCache(cacheAliasName, cacheKeyClass, cacheObjectClass);
        if(null != usersCache) {
            cacheData.setEnabled(!usersCache.isClosed());
        }
        cacheData.setCacheStatistics(getStatistics(usersCache));
        if(log.isDebugEnabled()) {
            log.debug("The cache data: {}", cacheData);
            log.debug("END :: Getting the statistics for cache: {}, key class: {} and cache object class: {}", cacheAliasName, cacheKeyClass, cacheObjectClass);
        }
        return cacheData;
    }

    @Override
    public CacheDetails getAllCacheStatistics(){
        if(log.isDebugEnabled()) {
            log.debug("START :: Getting the statistics for all the cache");
        }
        CacheDetails cacheDetails = new CacheDetails();
        List<CacheData> cacheDataList = null;
        Iterable<String> allCacheNames = cacheManager.getCacheNames();
        if(null != allCacheNames){
            cacheDataList = new ArrayList<>();
            for(String cacheAliasName : allCacheNames){
                Cache<String, Object> cacheObject =  cacheManager.getCache(cacheAliasName, String.class, Object.class);
                CacheData cacheData = new CacheData();
                cacheData.setName(cacheAliasName);
                if(null != cacheObject) {
                    cacheData.setEnabled(!cacheObject.isClosed());
                }
                cacheData.setCacheStatistics(getStatistics(cacheObject));
                cacheDataList.add(cacheData);
            }
        }
        if(null != cacheDataList && cacheDataList.size() > 0) {
            cacheDetails.setCacheData(cacheDataList);
        }
        if(log.isDebugEnabled()) {
            log.debug("The cache details: {}", cacheDetails);
            log.debug("END :: Getting the statistics for all the cache");
        }
        return cacheDetails;
    }

}
