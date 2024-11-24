package com.ravi.cache.statistics.service.impl;

import com.ravi.cache.statistics.entity.Teacher;
import com.ravi.cache.statistics.exception.RecordNotFoundException;
import com.ravi.cache.statistics.repository.TeacherRepository;
import com.ravi.cache.statistics.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            return teacher.get();
        } else {
            throw new RecordNotFoundException("No teacher record exists for the given id: " + id);
        }
    }

    @Override
    public Teacher createTeacher(Teacher entity) {
        return teacherRepository.save(entity);
    }

    @Override
    public Teacher updateTeacher(Teacher entity) {
        Optional<Teacher> teacher = teacherRepository.findById(entity.getId());
        Teacher newEntity = null;
        if (teacher.isPresent()) {
            newEntity = teacher.get();
            newEntity.setSubject(entity.getSubject());
            newEntity.setGrade(entity.getGrade());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
            newEntity.setEmail(entity.getEmail());
            newEntity = teacherRepository.save(newEntity);
        }
        return newEntity;
    }

    @Override
    public void deleteTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            teacherRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No teacher record exists for the given id: " + id);
        }
    }
}
