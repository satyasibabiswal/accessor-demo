package com.accessor.demo.common.vo;

public class CityProjectVO {
	
	private String searchCityName;
	private String searchCityId;
	private CityFilterOptionsVO filterOptionsVO;
	private String locale;
//Below fields related to pagination configuration 
	private String orderBy;
	private Integer pageno;
	private Integer totalRecordsPerPage;
	
	
	public String getSearchCityName() {
		return searchCityName;
	}
	public void setSearchCityName(String searchCityName) {
		this.searchCityName = searchCityName;
	}
	public String getSearchCityId() {
		return searchCityId;
	}
	public void setSearchCityId(String searchCityId) {
		this.searchCityId = searchCityId;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public CityFilterOptionsVO getFilterOptionsVO() {
		return filterOptionsVO;
	}
	public void setFilterOptionsVO(CityFilterOptionsVO filterOptionsVO) {
		this.filterOptionsVO = filterOptionsVO;
	}
	public Integer getPageno() {
		return pageno;
	}
	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}
	public Integer getTotalRecordsPerPage() {
		return totalRecordsPerPage;
	}
	public void setTotalRecordsPerPage(Integer totalRecordsPerPage) {
		this.totalRecordsPerPage = totalRecordsPerPage;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	

}
