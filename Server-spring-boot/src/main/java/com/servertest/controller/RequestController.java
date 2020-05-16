package com.servertest.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.servertest.model.entity.ExclusionAccount;
import com.servertest.model.entity.UserRequest;
import com.servertest.service.UserRequestService;

@RestController
@RequestMapping(value = "/api/v1/request")
public class RequestController {

	@Autowired
	private UserRequestService service;

	// Get List of Account Numbers
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<ExclusionAccount>> list() {
		final List<ExclusionAccount> acntList = service.list();

		if (acntList.isEmpty())
			return new ResponseEntity<List<ExclusionAccount>>(Collections.emptyList(), HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<ExclusionAccount>>(acntList, HttpStatus.OK);
	}

	// Get List of pending status account
	@RequestMapping(value = "/{status}", method = RequestMethod.GET)
	public ResponseEntity<List<UserRequest>> get(@PathVariable("status") String status) {
		final List<UserRequest> person = service.getUserList(status);

		if (null == person)
			return new ResponseEntity<List<UserRequest>>(Collections.emptyList(), HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<UserRequest>>(person, HttpStatus.OK);
	}

	// Create new accounts
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ResponseEntity<UserRequest> create(@Valid @RequestBody List<UserRequest> userRequest) {

		UserRequest persisted = null;
		for (UserRequest user : userRequest) {
			persisted = service.create(user);
		}

		if (null == persisted)
			return new ResponseEntity<UserRequest>(HttpStatus.NOT_MODIFIED);

		return new ResponseEntity<UserRequest>(persisted, HttpStatus.CREATED);
	}

	// Update account status (pending, approved, rejected)
	@RequestMapping(value = "/{accountNumber}", method = RequestMethod.PUT)
	public ResponseEntity<UserRequest> update(@Valid @RequestBody List<UserRequest> userRequest) {

		UserRequest persisted = null;
		for (UserRequest user : userRequest) {
			persisted = service.update(user);
		}
		if (null == persisted)
			return new ResponseEntity<UserRequest>(new UserRequest(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<UserRequest>(persisted, HttpStatus.OK);
	}

	// Delete Account based on account Number
	@RequestMapping(value = "/{accountNumber}", method = RequestMethod.DELETE)
	public ResponseEntity<UserRequest> delete(@PathVariable("accountNumber") Long accountNumber) {
		final UserRequest persisted = service.delete(accountNumber);

		if (null == persisted)
			return new ResponseEntity<UserRequest>(new UserRequest(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<UserRequest>(new UserRequest(), HttpStatus.NO_CONTENT);
	}

}
