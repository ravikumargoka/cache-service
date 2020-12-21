package com.ravi.cache.cachestatistics.service.impl;

import com.ravi.cache.cachestatistics.dto.CacheStatistics;
import lombok.extern.slf4j.Slf4j;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.management.CacheStatisticsMXBean;
import javax.management.MBeanServer;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Iterator;

import static com.ravi.cache.cachestatistics.constants.CacheConstants.*;

@Slf4j
public class BaseCacheStatisticsServiceImpl {
    protected CacheStatistics getStatistics(Cache<? extends Object, ? extends Object> cache) {
        CacheStatistics cacheStatistics = null;
        try {
            if (null != cache) {
                ObjectName objectName = getJMXObjectName(cache);
                MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

                final CacheStatisticsMXBean cacheStatisticsMXBean = MBeanServerInvocationHandler.newProxyInstance(mBeanServer, objectName, CacheStatisticsMXBean.class, false);
                // retrieving the cache statistics from MBeanServer.
                cacheStatistics = mapStatistics(cacheStatisticsMXBean, objectName);
            }
        } catch (Exception ex) {
            log.error("ERROR :: Exception occurred while getting the statistics for cache {} and the exception {}",
                    cache, ex);
            throw new RuntimeException(ex);
        }
        return cacheStatistics;
    }
    private ObjectName getJMXObjectName(Cache<? extends Object, ? extends Object> cache){
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        // Refer to org.ehcache.jsr107.Eh107CacheStatisticsMXBean.Eh107CacheStatisticsMXBean(String, URI, StatisticsService)
        // and org.ehcache.jsr107.Eh107MXBean.Eh107MXBean(String, URI, String)
        String cacheManagerName = sanitize(cache.getCacheManager().getURI().toString());
        String cacheName = sanitize(cache.getName());
        ObjectName objectName = null;
        try {
            objectName = new ObjectName(
                    "javax.cache:type=" + CACHE_STATISTICS_BEAN + ",CacheManager=" + cacheManagerName + ",Cache=" + cacheName);
        }
        catch (MalformedObjectNameException e) {
            throw new CacheException(e);
        }

        if(!mBeanServer.isRegistered(objectName)){
            throw new CacheException("No MBean found with ObjectName => " + objectName.getCanonicalName());
        }

        return objectName;
    }

    private String sanitize(String string) {
        return ((string == null) ? "" : string.replaceAll(",|:|=|\n", "."));
    }

    public <K extends Object, V extends Object> long getSize(Cache<K, V> cache) {
        Iterator<Cache.Entry<K, V>> itr = cache.iterator();
        long count = 0;
        while(itr.hasNext()){
            itr.next();
            count++;
        }
        return count;
    }

    public <K extends Object, V extends Object> String dump(Cache<K, V> cache) {
        Iterator<Cache.Entry<K, V>> itr = cache.iterator();
        StringBuffer b = new StringBuffer();
        while(itr.hasNext()){
            Cache.Entry<K, V> entry = itr.next();
            b.append(entry.getKey() + "=" + entry.getValue() + "\n");
        }
        return b.toString();
    }

    private CacheStatistics mapStatistics(CacheStatisticsMXBean cacheStatisticsMXBean, ObjectName objectName){
        CacheStatistics cacheStatistics = new CacheStatistics();
        try {
            cacheStatistics.setCacheHits(cacheStatisticsMXBean.getCacheHits());
            cacheStatistics.setCacheHitPercentage(cacheStatisticsMXBean.getCacheHitPercentage());
            cacheStatistics.setCacheMisses(cacheStatisticsMXBean.getCacheMisses());
            cacheStatistics.setCacheMissPercentage(cacheStatisticsMXBean.getCacheMissPercentage());
            cacheStatistics.setCacheGets(cacheStatisticsMXBean.getCacheGets());
            cacheStatistics.setCachePuts(cacheStatisticsMXBean.getCachePuts());
            cacheStatistics.setCacheRemovals(cacheStatisticsMXBean.getCacheRemovals());
            cacheStatistics.setCacheEvictions(cacheStatisticsMXBean.getCacheEvictions());
            cacheStatistics.setAverageGetTime(cacheStatisticsMXBean.getAverageGetTime()/1000f);
            cacheStatistics.setAveragePutTime(cacheStatisticsMXBean.getAveragePutTime()/1000f);
            cacheStatistics.setAverageRemoveTime(cacheStatisticsMXBean.getAverageRemoveTime()/1000f);
        }
        catch (Exception ex){
            log.error("Exception occurred while reading the cache statistics from Eh107CacheStatisticsMXBean", ex);
        }
        return cacheStatistics;
    }
}
