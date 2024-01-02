package com.cardearlership.dto;

import java.util.List;

public class VehicleCatalogueRetrieverDTO {
	private List<RequestDTO> requestDTOs;
	private Integer pageNumber;
	private Integer pageSize;
	private String[] orderBy;
	public List<RequestDTO> getRequestDTOs() {
		return requestDTOs;
	}
	public void setRequestDTOs(List<RequestDTO> requestDTOs) {
		this.requestDTOs = requestDTOs;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String[] getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String[] orderBy) {
		this.orderBy = orderBy;
	}
	
}
