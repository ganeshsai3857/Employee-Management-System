package com.gl.ems.repository;

import com.gl.ems.entity.Employee;
import com.gl.ems.entity.LoginCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginCredentialRepository extends JpaRepository<LoginCredential,Integer> {

    public LoginCredential findByUsername(String username);
}
