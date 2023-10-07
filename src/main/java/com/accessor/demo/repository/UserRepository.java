package com.accessor.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accessor.demo.entity.User;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
