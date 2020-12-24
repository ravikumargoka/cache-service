package com.ravi.cache.cachestatistics.repository;

import com.ravi.cache.cachestatistics.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
