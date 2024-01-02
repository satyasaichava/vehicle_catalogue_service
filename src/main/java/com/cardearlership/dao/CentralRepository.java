package com.cardearlership.dao;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.cardearlership.entity.BasicCarDetails;

@NoRepositoryBean
public interface CentralRepository extends Repository<BasicCarDetails, Integer>{
	Iterable<BasicCarDetails> saveAll(Iterable<BasicCarDetails> entities);
}
