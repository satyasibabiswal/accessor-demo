package com.accessor.demo.batch.processing;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.transaction.annotation.Transactional;
import com.accessor.demo.entity.StudentEntity;
public class ProjectInitiationJobWriter implements ItemWriter<StudentEntity> {
	private static final Logger log = LoggerFactory.getLogger(ProjectInitiationJobWriter.class);

	@Override
	public void write(List<? extends StudentEntity> listLovCityEnity) throws Exception {
		for(StudentEntity lovCityEntity: listLovCityEnity) {
			if(null!=lovCityEntity) {
				writeProject(lovCityEntity);
			}else {
				log.error("Initializing forecasted project: Invalid process id {}", lovCityEntity.getName());
				log.info("Initialization completed for forecasted project: {}", lovCityEntity.getName());
			}
		}
		
	}

	@Transactional
	private void writeProject(StudentEntity lovCityEntity) {
		if(lovCityEntity!=null) {
		  log.info("Project Country Name "+lovCityEntity.getName());	
		}
	}

}
