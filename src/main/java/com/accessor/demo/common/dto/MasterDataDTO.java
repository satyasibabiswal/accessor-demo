package com.accessor.demo.common.dto;
import java.util.List;

public class MasterDataDTO {
	
	private List<CityDetailDTO> cityList;

	public List<CityDetailDTO> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityDetailDTO> cityList) {
		this.cityList = cityList;
	}
	

	
}
