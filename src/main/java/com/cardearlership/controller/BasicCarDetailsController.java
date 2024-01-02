package com.cardearlership.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cardearlership.dto.RequestDTO;
import com.cardearlership.dto.VehicleCatalogueRetrieverDTO;
import com.cardearlership.entity.BasicCarDetails;
import com.cardearlership.service.BasicCarDetailsService;

@RestController
public class BasicCarDetailsController {
	@Autowired
	BasicCarDetailsService service;
	
	@PostMapping("/save")
	public BasicCarDetails saveAll(@RequestBody BasicCarDetails car) {
		return service.save(car);
	}
	
	@GetMapping("/getbyid")
	public ResponseEntity<BasicCarDetails> getById(@PathVariable Integer id){
		return new ResponseEntity<BasicCarDetails>(service.getById(id).get(),HttpStatus.OK);
	} 
	
	@GetMapping("/getall")
	public ResponseEntity<List<BasicCarDetails>> getAll(){
		return new ResponseEntity<List<BasicCarDetails>>(service.getAll(),HttpStatus.OK);
	}
	
	@PostMapping("/saveall")
	public List<BasicCarDetails> saveAll(@RequestBody List<BasicCarDetails> cars) {
		return service.saveAll(cars);
	}
	
	@PutMapping("/update")
	public ResponseEntity<BasicCarDetails> update(@RequestBody BasicCarDetails car) {
		return new ResponseEntity<BasicCarDetails>(service.update(car),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id){
		return new ResponseEntity<String>(service.delete(id),HttpStatus.OK);
	}
	
	@PostMapping("/filter")
	public ResponseEntity<List<BasicCarDetails>> filtering(@RequestBody BasicCarDetails car,@RequestParam Integer high, @RequestParam Integer low){
		return new ResponseEntity<List<BasicCarDetails>>(service.filtering(car,high,low),HttpStatus.OK);
	}
	
	@PostMapping("/filter2")
	public ResponseEntity<List<BasicCarDetails>> filtering2(@RequestBody VehicleCatalogueRetrieverDTO retrieverDTO){
		return new ResponseEntity<List<BasicCarDetails>>(service.filtering2(retrieverDTO),HttpStatus.OK);
	}
	

}
