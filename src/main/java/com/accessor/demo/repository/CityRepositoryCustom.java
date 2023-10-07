package com.accessor.demo.repository;

import org.springframework.data.domain.Page;

import com.accessor.demo.common.exception.ApplicationSystemException;
import com.accessor.demo.common.vo.CityProjectVO;
import com.accessor.demo.entity.LovCityEntity;

public interface CityRepositoryCustom {

	Page<LovCityEntity> getFiletredCityDetails(CityProjectVO activeProjectVO, Integer userId) throws ApplicationSystemException;

}
