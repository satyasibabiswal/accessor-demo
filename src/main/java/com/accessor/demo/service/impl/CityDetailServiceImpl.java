package com.accessor.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.accessor.demo.common.dto.CityDetailDTO;
import com.accessor.demo.common.dto.MasterDataDTO;
import com.accessor.demo.common.exception.ApplicationSystemException;
import com.accessor.demo.repository.CityRepository;
import com.accessor.demo.service.CityDetailService;

@Service
public class CityDetailServiceImpl implements CityDetailService {
	
	@Autowired
	private CityRepository cityRepository;
    
	@Override
	@Cacheable(value = "masterDataCache")
	public MasterDataDTO getCityDetail() throws ApplicationSystemException {
		MasterDataDTO masterDataDTO=new MasterDataDTO();
		List<CityDetailDTO> cityList=new ArrayList<>();
		
		try {
			cityRepository.findAll().stream().forEach( e->{
				CityDetailDTO genericDTO=new CityDetailDTO(e.getCountryId(),e.getCountryName(),
						e.getCountryCode(),e.getDistrict(),e.getPopulation());
				cityList.add(genericDTO);
			});
    masterDataDTO.setCityList(cityList);
	}catch(DataAccessException e) {
	}catch (Exception e) {
	}
	return masterDataDTO;
}
}
