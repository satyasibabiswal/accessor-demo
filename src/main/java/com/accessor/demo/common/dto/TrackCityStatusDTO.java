package com.accessor.demo.common.dto;

import java.util.List;

public class TrackCityStatusDTO {
	
	private String countryCode;
	private String district;
	private Integer population;
	
	public TrackCityStatusDTO() {
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}
	
	

}
