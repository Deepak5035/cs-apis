package com.clinic.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.entity.AddressEntity;

@Repository
@Transactional
public interface AddressDao extends JpaRepository<AddressEntity, Long> {

}
