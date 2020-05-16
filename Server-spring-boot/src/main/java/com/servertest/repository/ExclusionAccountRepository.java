package com.servertest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servertest.model.entity.ExclusionAccount;

public interface ExclusionAccountRepository extends JpaRepository<ExclusionAccount, Long> {

}
