package com.ravi.cache.cachestatistics.service.impl;

import com.ravi.cache.cachestatistics.dto.Teacher;
import com.ravi.cache.cachestatistics.dto.Teachers;
import com.ravi.cache.cachestatistics.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    @Override
    public Teachers getAllTeachers() {
        if(log.isDebugEnabled()){
            log.debug("START :: Getting all teachers");
        }
        Teacher teacher1 = new Teacher();
        teacher1.setId(12736);
        teacher1.setGrade("First Grade");
        teacher1.setSubject("General Science");
        teacher1.setFirstName("Bill");
        teacher1.setLastName("Clinton");

        Teacher teacher2 = new Teacher();
        teacher2.setId(473782);
        teacher2.setGrade("Second Grade");
        teacher2.setSubject("Mathematics");
        teacher2.setFirstName("Donald");
        teacher2.setLastName("Trump");


        Teacher teacher3 = new Teacher();
        teacher3.setId(57383);
        teacher3.setGrade("Third Grade");
        teacher3.setSubject("English");
        teacher3.setFirstName("George");
        teacher3.setLastName("Bush");

        List<Teacher> teachersList = new ArrayList<>();
        teachersList.add(teacher1);
        teachersList.add(teacher2);
        teachersList.add(teacher3);

        Teachers teachers = new Teachers();
        teachers.setTeachers(teachersList);

        if(log.isDebugEnabled()){
            log.debug("END :: Getting all teachers: {}", teachers);
        }
        return teachers;
    }
}
