package com.playground.api.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class BatchConfiguration {

    @Bean
    public Job sampleJob(Step sampleStep, JobRepository jobRepository) {
        return new JobBuilder("sampleJob", jobRepository)
                .start(sampleStep)
                .build();
    }

    @Bean
    public Step sampleStep(ItemReader<String> reader,
                           ItemProcessor<String, String> processor,
                           ItemWriter<String> writer,
                           JobRepository jobRepository,
                           PlatformTransactionManager transactionManager) {
        return new StepBuilder("sampleStep", jobRepository)
                .<String, String>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


    @Bean
    public ItemReader<String> reader() {
        return new ListItemReader<>(List.of("Item1", "Item2", "Item3"));
    }

    @Bean
    public ItemProcessor<String, String> processor() {
        return item -> "Processed " + item;
    }

    @Bean
    public ItemWriter<String> writer() {
        return items -> items.forEach(System.out::println);
    }
}