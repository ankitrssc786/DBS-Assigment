
package com.servertest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servertest.model.entity.ExclusionAccount;
import com.servertest.model.entity.UserRequest;
import com.servertest.repository.ExclusionAccountRepository;
import com.servertest.repository.UserRequestRepository;
import com.servertest.service.UserRequestService;

@Service
public class UserRequestServiceImpl implements UserRequestService {

	@Autowired
	private UserRequestRepository userRepository;

	@Autowired
	private ExclusionAccountRepository exclRepository;

	@Override
	public List<ExclusionAccount> list() {
		return exclRepository.findAll();
	}

	@Override
	public List<UserRequest> getUserList(String status) {
		return userRepository.getUserList(status);
	}

	@Transactional
	@Override
	public UserRequest create(UserRequest userRequest) {
		return userRepository.saveorUpdate(userRequest);
	}

	@Transactional
	@Override
	public UserRequest update(UserRequest user) {
		final UserRequest persisted = userRepository.getOne(user.getAccountNumber());
		ExclusionAccount exclAccount = new ExclusionAccount();

		if (null == persisted)
			return null;
		if (user.isCheckFlag())
			persisted.setStatus(user.getStatus());
		UserRequest userSaveResult = userRepository.saveorUpdate(persisted);

		if (user.getStatus().equalsIgnoreCase("approved") && userSaveResult.getStatus().equalsIgnoreCase("approved")) {
			exclAccount.setAccountNumber(user.getAccountNumber());
			exclRepository.save(exclAccount);
		}

		return userSaveResult;
	}
	
	@Transactional
	@Override
	public void delete(Long accountNumber) {
		userRepository.delete(accountNumber);
	}

}