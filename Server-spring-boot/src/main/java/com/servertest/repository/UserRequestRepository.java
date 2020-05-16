
 package com.servertest.repository;
 
 import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.servertest.model.entity.UserRequest;

 public interface UserRequestRepository extends JpaRepository<UserRequest, Long> {
	 
	 @Query("SELECT * FROM user_request u WHERE u.status = ?")
	 List<UserRequest> findUserByStatus(String status);
 
 }
 