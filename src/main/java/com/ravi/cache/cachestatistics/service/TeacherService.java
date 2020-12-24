package com.ravi.cache.cachestatistics.service;

import com.ravi.cache.cachestatistics.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeachers();

    Teacher getTeacherById(Long id);

    Teacher createOrUpdateTeacher(Teacher entity);

    void deleteTeacherById(Long id);
}
