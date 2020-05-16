package com.servertest.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * The Exclusion Account Entity Model.
 * 
 *
 */
@Entity
@Table(name = "exclusion_account")
public class ExclusionAccount implements Serializable {

	private static final long serialVersionUID = 2277957532205899169L;

	@Size(min = 1, max = 20)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_number")
	private Long accountNumber;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

}
