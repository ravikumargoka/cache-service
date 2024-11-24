package com.ravi.cache.statistics.controller;

import com.ravi.cache.statistics.entity.Teacher;
import com.ravi.cache.statistics.exception.RecordNotFoundException;
import com.ravi.cache.statistics.manager.TeacherServiceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/teachers/v1")
public class TeacherController {

    private final TeacherServiceManager teacherServiceManager;

    public TeacherController(TeacherServiceManager teacherServiceManager) {
        this.teacherServiceManager = teacherServiceManager;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teacherList = teacherServiceManager.getAllTeachers();
        return new ResponseEntity<>(teacherList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") Long id) {
        Teacher teacher = teacherServiceManager.getTeacherById(id);
        return new ResponseEntity<>(teacher, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Teacher> createOrUpdateTeacher(Teacher teacher) throws RecordNotFoundException {
        Teacher updated = teacherServiceManager.createOrUpdateUser(teacher);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteTeacherById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        teacherServiceManager.deleteTeacherById(id);
        return HttpStatus.FORBIDDEN;
    }
}
