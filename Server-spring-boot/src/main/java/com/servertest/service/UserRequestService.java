package com.servertest.service;

import java.util.List;

import com.servertest.model.entity.ExclusionAccount;
import com.servertest.model.entity.UserRequest;

public interface UserRequestService {

	List<ExclusionAccount> list();

	List<UserRequest> getUserList(String status);

	UserRequest create(UserRequest userRequest);

	UserRequest update(UserRequest person);

	UserRequest delete(Long accountNumber);
}
