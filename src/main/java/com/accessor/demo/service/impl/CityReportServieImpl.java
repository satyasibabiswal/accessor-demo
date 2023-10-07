package com.accessor.demo.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import com.accessor.demo.entity.LovCityEntity;
import com.accessor.demo.repository.CityRepository;
import com.accessor.demo.service.CityReportServie;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class CityReportServieImpl implements CityReportServie {

	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
		String path="E:\\local\\web";
		List<LovCityEntity> listCityDetail = cityRepository.findAll();
		//Load file and compile it
		File file=ResourceUtils.getFile("classpath:cityDetail.jrxml");
		JasperReport compileReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(listCityDetail);
		Map<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("Created BY", "Satya");
		JasperPrint jasperPrint=JasperFillManager.fillReport(compileReport,parameters,dataSource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\city.html");
		}
      if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\city.pdf");
		}
		return "Report generated in the "+path;
	}
	

}
