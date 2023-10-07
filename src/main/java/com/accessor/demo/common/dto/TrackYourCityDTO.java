package com.accessor.demo.common.dto;

import java.util.List;

public class TrackYourCityDTO {
	
	//This we will send the data part as of pagination
	private List<TrackCityStatusDTO> cityStatusList;
	
	//This is common for pagination
	private Integer totalRecords;
	private Integer pageNumber;
	private Integer totalPages;
	private Integer totalRecordsPerPage;
	
	public TrackYourCityDTO() {
	}

	public List<TrackCityStatusDTO> getCityStatusList() {
		return cityStatusList;
	}

	public void setCityStatusList(List<TrackCityStatusDTO> cityStatusList) {
		this.cityStatusList = cityStatusList;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalRecordsPerPage() {
		return totalRecordsPerPage;
	}

	public void setTotalRecordsPerPage(Integer totalRecordsPerPage) {
		this.totalRecordsPerPage = totalRecordsPerPage;
	}
	
	

}
