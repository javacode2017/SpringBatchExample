package config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import model.User;

@Configuration
public class SpringBatchConfig {

	@Bean
	public Job job(JobBuilderFactory jobBiBuilderFactory,
							StepBuilderFactory stepBuilderFactory,
							ItemReader<User> itemReader,
							ItemProcessor<User, User> itemProcessor,
							ItemWriter<User> itemWriter) {
		
		Step step = stepBuilderFactory.get("ETL-File-Load")
									.<User,User>chunk(100)
									.reader(itemReader)
									.processor(itemProcessor)
									.writer(itemWriter)
									.build();
		Job job = jobBiBuilderFactory.get("ETL-Load")
							.incrementer(new RunIdIncrementer())
							.start(step)
							.build();
		return job;
	}
}
