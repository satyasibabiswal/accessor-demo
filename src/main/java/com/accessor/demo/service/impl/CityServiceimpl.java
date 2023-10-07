package com.accessor.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.accessor.demo.common.dto.TrackCityStatusDTO;
import com.accessor.demo.common.dto.TrackYourCityDTO;
import com.accessor.demo.common.exception.ApplicationSystemException;
import com.accessor.demo.common.service.CityService;
import com.accessor.demo.common.util.CommonConstants;
import com.accessor.demo.common.vo.CityProjectVO;
import com.accessor.demo.entity.LovCityEntity;
import com.accessor.demo.repository.CityRepositoryCustom;


@Service
public class CityServiceimpl implements CityService {
	
	@Autowired
	CityRepositoryCustom cityRepositoryCustom;

	@Override
	public TrackYourCityDTO findInprogressCity(Integer userId, CityProjectVO activeProjectVO, String locale) throws ApplicationSystemException {
		
		TrackYourCityDTO trackYourCityDTO=new TrackYourCityDTO();
		TrackCityStatusDTO TrackCityStatusDTO=new TrackCityStatusDTO();
		List<TrackCityStatusDTO> listTrackCityStatusDTO=new ArrayList<TrackCityStatusDTO>();
		locale = "en";
		
		if (userId == null) {
			throw new ApplicationSystemException(CommonConstants.USER_DOESNOT_EXISTS, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Page<LovCityEntity> cityEntityList = cityRepositoryCustom.getFiletredCityDetails(activeProjectVO,userId);
		if(cityEntityList !=null && cityEntityList.hasContent()) {
			for(LovCityEntity lovCityEntity: cityEntityList) {
				TrackCityStatusDTO.setCountryCode(lovCityEntity.getCountryCode());
				TrackCityStatusDTO.setDistrict(lovCityEntity.getDistrict());
				TrackCityStatusDTO.setPopulation(lovCityEntity.getPopulation());
				listTrackCityStatusDTO.add(TrackCityStatusDTO);
			}
			trackYourCityDTO.setCityStatusList(listTrackCityStatusDTO);
			trackYourCityDTO.setPageNumber(cityEntityList.getPageable().getPageNumber()+1);
			trackYourCityDTO.setTotalPages(cityEntityList.getTotalPages());
			trackYourCityDTO.setTotalRecords(Math.toIntExact(cityEntityList.getTotalElements()));
			trackYourCityDTO.setTotalRecordsPerPage(cityEntityList.getPageable().getPageSize());
		}
		return trackYourCityDTO;
	}

}
