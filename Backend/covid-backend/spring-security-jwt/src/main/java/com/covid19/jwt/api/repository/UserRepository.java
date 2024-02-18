package com.covid19.jwt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid19.jwt.api.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
    User findByEmail(String email);
}
