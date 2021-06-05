package com.biblequizappbackend.login;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long>{
	Login findByUsername(String username);
}
