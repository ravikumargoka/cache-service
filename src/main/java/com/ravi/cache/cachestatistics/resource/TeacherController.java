package com.ravi.cache.cachestatistics.resource;

import com.ravi.cache.cachestatistics.entity.Teacher;
import com.ravi.cache.cachestatistics.exception.RecordNotFoundException;
import com.ravi.cache.cachestatistics.manager.TeacherServiceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/teachers/v1")
public class TeacherController {

    @Autowired
    private TeacherServiceManager teacherServiceManager;

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
