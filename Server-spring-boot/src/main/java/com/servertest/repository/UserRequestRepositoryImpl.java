package com.servertest.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.servertest.model.entity.ExclusionAccount;
import com.servertest.model.entity.UserRequest;

@Repository
public class UserRequestRepositoryImpl implements UserRequestRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<ExclusionAccount> list() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("from exclusion_account");
		List<ExclusionAccount> list = query.list();
		return list;
	}

	@Override
	public List<UserRequest> getUserList(String status) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("select u from user_request u where u.status = :status");
		query.setParameter("status", status);
		List<UserRequest> list = query.list();
		return list;
	}

	@Override
	public UserRequest saveorUpdate(UserRequest userRequest) {
		Session currentSession = entityManager.unwrap(Session.class);
		UserRequest result = (UserRequest) currentSession.save(userRequest);
		return result;
	}

	@Override
	public UserRequest getOne(Long accountNumber) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("select u from user_request u where u.accountNumber = :accountNumber");
		query.setParameter("accountNumber", accountNumber);
		UserRequest result = (UserRequest) query.list();
		return result;
	}

	@Override
	public void delete(Long accountNumber) {
		Session currentSession = entityManager.unwrap(Session.class);
		UserRequest userRequest = currentSession.get(UserRequest.class, accountNumber);
		currentSession.delete(userRequest);
	}

}
