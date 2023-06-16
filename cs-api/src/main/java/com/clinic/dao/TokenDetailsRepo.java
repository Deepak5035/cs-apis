package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.clinic.entity.TokenEntity;


@Repository
@Transactional
public interface TokenDetailsRepo extends JpaRepository<TokenEntity, Long> {

	@Query(value ="Select * from tbl_token_detail where id =:id ", nativeQuery = true)
	TokenEntity findOneByLid(Long id);
}
