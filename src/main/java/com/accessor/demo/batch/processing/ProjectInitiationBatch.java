package com.accessor.demo.batch.processing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import com.accessor.demo.entity.StudentEntity;
import com.accessor.demo.repository.StudentRepository;

@EnableBatchProcessing
@Configuration
public class ProjectInitiationBatch {
private static final Logger log = LoggerFactory.getLogger(ProjectInitiationBatch.class);
	
    @Autowired
    private JobLauncher jobLauncher;
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	@Autowired
	public StudentRepository studentRepository;
	
	@Async
	@Scheduled(cron = "${project.init.batch.cron.job.expression}", zone = "${app.timezone}")
	public void printMessage() {
		try {
			log.info("Project job scheduler started:");
			JobParameters jobParameters = new JobParametersBuilder().addLong(
					"time", System.currentTimeMillis()).toJobParameters();
			jobLauncher.run(projectInitiationNotify(), jobParameters);
			log.info("Project creation batch processing scheduled successfully");
		} catch (Exception exception) {
			log.error("Error while schedule Project creation batch process:"+exception);
		}
	}
	
	@Bean
	public Job projectInitiationNotify() {
		return jobBuilderFactory.get("projectInitiationNotify")
				.incrementer(new RunIdIncrementer())
				.flow(projectInitiationNotifyStep())
				.end()
				.build();
	}
	
	@Bean
	public Step projectInitiationNotifyStep() {
		RepositoryItemReader<StudentEntity> repositoryItemReader = new RepositoryItemReader<StudentEntity>();
		repositoryItemReader.setRepository(studentRepository);
		repositoryItemReader.setMethodName("findAllName"); //Repository Method name
		List<String> arguments = new ArrayList<>();
		//arguments.add("New");
		repositoryItemReader.setArguments(arguments);
		Map<String, Direction> sort = new HashMap<>();
	    //sort.put("creationTs", Direction.ASC);
	    repositoryItemReader.setSort(sort);
		return stepBuilderFactory.get("projectInitiationNotifyStep")
				.<StudentEntity,StudentEntity> chunk(0)
				.reader(repositoryItemReader)
				.processor(initProjectBatchProcessor()) //Processor class method
				.writer(initWriterProject())                  //Writer class method
				.build();
	}
	@Bean
	public ProjectInitiationQueueProcessor initProjectBatchProcessor() {
		return new ProjectInitiationQueueProcessor();
	}
	
	@Bean
	public ItemWriter<StudentEntity> initWriterProject() {
		return new ProjectInitiationJobWriter();
	}

	
	

}
