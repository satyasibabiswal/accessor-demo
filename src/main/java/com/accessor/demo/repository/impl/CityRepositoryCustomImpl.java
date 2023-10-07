package com.accessor.demo.repository.impl;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.accessor.demo.common.exception.ApplicationSystemException;
import com.accessor.demo.common.vo.CityFilterOptionsVO;
import com.accessor.demo.common.vo.CityProjectVO;
import com.accessor.demo.entity.LovCityEntity;
import com.accessor.demo.repository.CityRepositoryCustom;

@Repository
public class CityRepositoryCustomImpl implements CityRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	private static final Logger log = LoggerFactory.getLogger(CityRepositoryCustomImpl.class);
	
	@Override
	public Page<LovCityEntity> getFiletredCityDetails(CityProjectVO activeProjectVO, Integer userId) throws ApplicationSystemException {
		
		try {
		Pageable pageRequest=PageRequest.of(activeProjectVO.getPageno(), activeProjectVO.getTotalRecordsPerPage(), Sort.by(Direction.DESC,activeProjectVO.getOrderBy()));
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<LovCityEntity> criteriaQuery = criteriaBuilder.createQuery(LovCityEntity.class);
		Root<LovCityEntity> root = criteriaQuery.from(LovCityEntity.class);
		List<Predicate> predicates = new ArrayList<>();
		CityFilterOptionsVO filterOptionsVO = activeProjectVO.getFilterOptionsVO(); 
		
		//Begin attribute Many to One relationship in the table 
		if(filterOptionsVO!=null && filterOptionsVO.getCountryCode()!=null && !filterOptionsVO.getCountryCode().isEmpty()) {
			predicates.add(criteriaBuilder.and(root.join("").get("").in(filterOptionsVO)));	
		}
		if(filterOptionsVO!=null && filterOptionsVO.getDistrict()!=null && !filterOptionsVO.getDistrict().isEmpty()) {
			predicates.add(criteriaBuilder.and(root.join("").get("").in(filterOptionsVO)));	
		}
		if(filterOptionsVO!=null && filterOptionsVO.getPopulation()!=null && !filterOptionsVO.getPopulation().isEmpty()) {
			predicates.add(criteriaBuilder.and(root.join("").get("").in(filterOptionsVO)));	
		}
		// End attribute Many to One relationship in the table
		
		criteriaQuery.select(root).distinct(true);
		TypedQuery<LovCityEntity> totalRecordQuery = entityManager.createQuery(criteriaQuery);
		TypedQuery<LovCityEntity> pageQuery = entityManager.createQuery(criteriaQuery);
		pageQuery.setFirstResult(pageRequest.getPageSize() * pageRequest.getPageNumber());
		pageQuery.setMaxResults(pageRequest.getPageSize());
		List<LovCityEntity> pageQueryResultList = pageQuery.getResultList();
		Page<LovCityEntity> pageresult = new PageImpl<LovCityEntity>(pageQueryResultList, pageRequest,
				totalRecordQuery.getResultList().size());
		return pageresult;
		 }catch (Exception exception) {
			log.error("Error while fetching project details:", exception);
			throw new ApplicationSystemException(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
