package com.ravi.cache.cachestatistics.repository;

import com.ravi.cache.cachestatistics.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
