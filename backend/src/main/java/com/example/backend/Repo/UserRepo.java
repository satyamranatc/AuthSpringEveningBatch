package com.example.backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Models.UserModel;

public interface UserRepo extends JpaRepository<UserModel,Long> {

     UserModel findByUsername(String username);



    
}
