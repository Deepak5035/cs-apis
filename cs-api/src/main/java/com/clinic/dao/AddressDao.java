package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.AddressEntity;

public interface AddressDao extends JpaRepository<AddressEntity, Long> {

}
