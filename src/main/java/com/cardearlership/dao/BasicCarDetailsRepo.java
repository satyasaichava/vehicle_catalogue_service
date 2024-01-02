package com.cardearlership.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cardearlership.entity.BasicCarDetails;

public interface BasicCarDetailsRepo extends JpaRepository<BasicCarDetails, Integer>,JpaSpecificationExecutor<BasicCarDetails>{
	
}
