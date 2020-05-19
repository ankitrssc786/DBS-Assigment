
package com.servertest.repository;

import java.util.List;

import com.servertest.model.entity.ExclusionAccount;
import com.servertest.model.entity.UserRequest;

public interface UserRequestRepository {

	List<ExclusionAccount> list();

	List<UserRequest> getUserList(String status);

	UserRequest saveorUpdate(UserRequest userRequest);
	
	UserRequest getOne(Long accountNumber);

	void delete(Long accountNumber);

}
