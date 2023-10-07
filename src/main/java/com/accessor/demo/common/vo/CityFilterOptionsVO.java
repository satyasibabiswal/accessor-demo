package com.accessor.demo.common.vo;

import java.util.List;

public class CityFilterOptionsVO {
	
	private List<String> countryCode;
	private List<String> district;
	private List<Integer> population;
	
	public List<String> getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(List<String> countryCode) {
		this.countryCode = countryCode;
	}
	public List<String> getDistrict() {
		return district;
	}
	public void setDistrict(List<String> district) {
		this.district = district;
	}
	public List<Integer> getPopulation() {
		return population;
	}
	public void setPopulation(List<Integer> population) {
		this.population = population;
	}
	
	

}
