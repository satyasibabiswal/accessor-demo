package com.accessor.demo.service;

import java.io.FileNotFoundException;

import net.sf.jasperreports.engine.JRException;

public interface CityReportServie {
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException;

}
