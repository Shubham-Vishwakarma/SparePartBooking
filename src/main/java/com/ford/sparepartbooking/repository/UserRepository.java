package com.ford.sparepartbooking.repository;

import com.ford.sparepartbooking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}