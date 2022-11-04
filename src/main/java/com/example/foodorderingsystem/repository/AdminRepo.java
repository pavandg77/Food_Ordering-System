package com.example.foodorderingsystem.repository;

import com.example.foodorderingsystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {
    Admin findByEmailidAndPassword(String emailId , String password);
    Admin findByEmailid(String emailId);

}
