package config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBatchConfig {

	@Bean
	public Job job(JobBuilderFactory jobBiBuilderFactory) {
		
		Step step = null;
		Job job = jobBiBuilderFactory.get("ETL-Load")
							.incrementer(new RunIdIncrementer())
							.start(step)
							.build();
		return job;
	}
}
