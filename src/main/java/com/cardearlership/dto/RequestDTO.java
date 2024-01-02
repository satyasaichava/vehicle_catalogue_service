package com.cardearlership.dto;

import java.util.List;

public class RequestDTO {
	private String field;
	private List<String> value;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}

	
}
