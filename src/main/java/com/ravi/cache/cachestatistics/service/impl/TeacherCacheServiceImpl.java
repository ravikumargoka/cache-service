package com.ravi.cache.cachestatistics.service.impl;

import com.ravi.cache.cachestatistics.dto.Teachers;
import com.ravi.cache.cachestatistics.service.TeacherCacheService;
import com.ravi.cache.cachestatistics.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.CacheManager;

import static com.ravi.cache.cachestatistics.constants.CacheConstants.TEACHER_CACHE_ALIAS;
import static com.ravi.cache.cachestatistics.constants.CacheConstants.TEACHER_CACHE_KEY;

@Component
@Slf4j
public class TeacherCacheServiceImpl implements TeacherCacheService {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public Teachers getAllTeachers(){
        if(log.isDebugEnabled()){
            log.debug("START :: Getting all teachers");
        }
        Teachers teachers;
        Cache<String, Object> teachersCache = cacheManager.getCache(TEACHER_CACHE_ALIAS, String.class, Object.class);
        teachers = (Teachers)teachersCache.get(TEACHER_CACHE_KEY);

        if(null == teachers){
            if(log.isDebugEnabled()){
                log.debug("Data Not found in Cache: {}", teachers);
                log.debug("Reading user information from service/DB");
            }
            teachers = teacherService.getAllTeachers();
            teachersCache.put(TEACHER_CACHE_KEY, teachers);
        }
        if(log.isDebugEnabled()){
            log.debug("END :: Getting all teachers: {}", teachers);
        }
        return teachers;
    }
}
