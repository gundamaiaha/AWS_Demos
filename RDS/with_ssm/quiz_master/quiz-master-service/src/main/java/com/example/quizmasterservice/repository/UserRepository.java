package com.example.quizmasterservice.repository;

import com.example.quizmasterservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
