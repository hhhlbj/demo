package com.hhhlbjshop.backend.repository;

import com.hhhlbjshop.backend.entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserTest,String> {
}
