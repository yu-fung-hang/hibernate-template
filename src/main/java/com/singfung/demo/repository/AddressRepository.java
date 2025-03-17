package com.singfung.demo.repository;

import java.io.Serializable;

import com.singfung.demo.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Serializable> {

}