package com.ravi.cache.statistics.service;

import com.ravi.cache.statistics.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeachers();

    Teacher getTeacherById(Long id);

    Teacher createTeacher(Teacher entity);

    Teacher updateTeacher(Teacher entity);

    void deleteTeacherById(Long id);
}
