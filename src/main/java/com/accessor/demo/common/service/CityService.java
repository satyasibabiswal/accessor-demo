package com.accessor.demo.common.service;

import com.accessor.demo.common.dto.TrackYourCityDTO;
import com.accessor.demo.common.exception.ApplicationSystemException;
import com.accessor.demo.common.vo.CityProjectVO;

public interface CityService {

	TrackYourCityDTO findInprogressCity(Integer userId, CityProjectVO activeProjectVO, String locale) throws ApplicationSystemException;

}
