package com.servertest.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Exclusion Account Entity Model.
 * 
 *
 */
@Entity
@Table(name = "exclusion_account")
@XmlRootElement
public class ExclusionAccount {

	@Id
	@GeneratedValue
	private long id;

	@Size(min = 1, max = 20)
	@OneToOne(cascade = CascadeType.ALL, targetEntity = UserRequest.class)
	@JoinColumn(name = "account_number")
	private Long accountNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

}
