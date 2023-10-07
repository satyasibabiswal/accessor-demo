package com.accessor.demo.batch.processing;

import org.springframework.batch.item.ItemProcessor;

import com.accessor.demo.entity.StudentEntity;


public class ProjectInitiationQueueProcessor implements ItemProcessor<StudentEntity, StudentEntity> {

	@Override
	public StudentEntity process(StudentEntity cityEnity) throws Exception {
		return cityEnity;
	}

}
