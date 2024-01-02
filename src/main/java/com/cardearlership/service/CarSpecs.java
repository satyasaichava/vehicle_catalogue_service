package com.cardearlership.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cardearlership.dto.RequestDTO;
import com.cardearlership.entity.BasicCarDetails;

import jakarta.persistence.criteria.Predicate;

@Service
public class CarSpecs {
	public Specification<BasicCarDetails> filterSpec(RequestDTO requestDTO){
		/*
		 * Predicate predicate = root.get(requestDTO.getField()).in(requestDTO.getValue());
		 * root.get(name_of_the_property)
		 * .in(a_set_of_values(LIST))
		 * root is like where condition or here we are calling it as PREDICATE
		 * criteriaBuilder.and(PREDICATE)
		 * 
		 * Now the return statement gives a specification. This specification is 
		 * used in findAll(Specification) to retrieve all the fields that match.
		 */
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.and(root.get(requestDTO.getField()).in(requestDTO.getValue()));
		};
	}
	
	public Specification<BasicCarDetails> filterByAllFields(List<RequestDTO> requestDTOs){
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			for(RequestDTO requestDTO : requestDTOs) {
				if(!requestDTO.getField().equalsIgnoreCase("price"))
					predicates.add(root.get(requestDTO.getField()).in(requestDTO.getValue()));
				else {
					List<String> prices = requestDTO.getValue();
					predicates.add(criteriaBuilder.between(root.get(requestDTO.getField()), prices.get(0), prices.get(1)));
				}
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
