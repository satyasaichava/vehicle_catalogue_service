package com.cardearlership.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cardearlership.dao.BasicCarDetailsRepo;
import com.cardearlership.dto.RequestDTO;
import com.cardearlership.dto.VehicleCatalogueRetrieverDTO;
import com.cardearlership.entity.BasicCarDetails;

@Service
public class BasicCarDetailsService {
	
	@Autowired
	BasicCarDetailsRepo repo;
	
	@Autowired
	CarSpecs carSpecs;
	public Optional<BasicCarDetails> getById(Integer id) {
		return repo.findById(id);
	}
	
	/*
	 * Get a set of new cars' details*/
	public List<BasicCarDetails> getAll() {
		return (List<BasicCarDetails>) repo.findAll();
	}	
	
	/*
	 * Save new Car Details*/
	public BasicCarDetails save(BasicCarDetails car) {
		return repo.save(car);
	}	
	
	/*
	 * Get a set of new cars' details*/
	public List<BasicCarDetails> saveAll(List<BasicCarDetails> cars) {
		return (List<BasicCarDetails>) repo.saveAll(cars);
	}
	
	/*
	 * Update some car details
	 */
	public BasicCarDetails update(BasicCarDetails car) {
		Optional<BasicCarDetails> optional = getById(car.getCid());
		if(!optional.isEmpty()) {
			BasicCarDetails oldCar = optional.get();
			if(car.getBrand()!=null) {
				oldCar.setBrand(car.getBrand());
			}
			if(car.getColor()!=null) {
				oldCar.setColor(car.getColor());
			}
			if(car.getModel()!=null) {
				oldCar.setModel(car.getModel());
			}
			if(car.getPrice()!=null) {
				oldCar.setPrice(car.getPrice());
			}
			if(car.getType()!=null) {
				oldCar.setType(car.getType());
			}
			if(car.getYear()!=null) {
				oldCar.setYear(car.getYear());
			}
			return repo.save(oldCar);
		}
		return null;
		
	}
	
	/*
	 * Delete a car
	 */
	public String delete(Integer id) {
		repo.deleteById(id);
		return "Car Record Deleted Successfully";
	}

	/*
	 * Filtering the cars based on the properties like make, model, price range etc..
	 */
	public List<BasicCarDetails> filtering(BasicCarDetails car, Integer high, Integer low){
		//custom filtering and on top of the result list filtered based on price range
		ExampleMatcher exm = ExampleMatcher.matching()
											.withIgnoreCase();		
		Example<BasicCarDetails> ex = Example.of(car, exm);	
		List<BasicCarDetails> list = repo.findAll(ex);
		//Logic for price range 
		if(high!=null && low!=null) {
			return list.stream().filter((var)->var.getPrice()<=high && var.getPrice()>=low).collect(Collectors.toList());
		}else if(high==null && low!=null) {
			return list.stream().filter((var)->var.getPrice()>=low).collect(Collectors.toList());
		}else if(high!=null && low==null) {
			return list.stream().filter((var)->var.getPrice()<=high).toList();
		}else {
			return list;
		}
	}
	
	/*
	 * Filtering using specification and Pagination
	 */
	public List<BasicCarDetails> filtering2(VehicleCatalogueRetrieverDTO retrieverDTO){
		return repo.findAll(carSpecs.filterByAllFields(retrieverDTO.getRequestDTOs()),PageRequest.of(retrieverDTO.getPageNumber(), retrieverDTO.getPageSize(), Sort.by(retrieverDTO.getOrderBy()))).toList();
	}
	

}
