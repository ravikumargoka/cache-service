package com.ravi.cache.cachestatistics.resource;

import com.ravi.cache.cachestatistics.dto.Teachers;
import com.ravi.cache.cachestatistics.service.TeacherCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/teacher/v1")
public class TeacherResource {

    @Autowired
    private TeacherCacheService teacherCacheService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Teachers> getAllTeachers(){

        Teachers teachers = teacherCacheService.getAllTeachers();

        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }
}
