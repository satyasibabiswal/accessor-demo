package com.accessor.demo.service;

import com.accessor.demo.common.dto.MasterDataDTO;
import com.accessor.demo.common.exception.ApplicationSystemException;

public interface CityDetailService {

	MasterDataDTO getCityDetail() throws ApplicationSystemException;

}
