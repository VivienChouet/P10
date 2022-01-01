package com.bibliotheque.batch.Service;

import com.bibliotheque.batch.DTO.AttenteDTO;
import com.bibliotheque.batch.DTO.ReservationDTO;
import com.bibliotheque.batch.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchService {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobLauncher launcher;

    Logger logger = LoggerFactory.getLogger(LoggingController.class);


@Bean
    public Job processJob() {
    return  jobs.get("processJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener())
            .flow(step1()).next(step2())
            .end()
            .build();
}

@Bean
    Step step1 (){
    logger.info("Lauch step1");
    return steps.get("step1").<ReservationDTO, ReservationDTO>chunk(1)
            .reader(new ReaderReservation())
            .processor(new Processor())
            .writer(new WriterReservation())
            .build();
}


    @Bean
    Step step2 (){
        logger.info("Lauch step2");
        return steps.get("step2").<AttenteDTO, AttenteDTO>chunk(1)
                .reader(new ReaderAttente())
                 .writer(new WriterAttente())
                .build();
    }

@Bean
    JobExecutionListener listener(){
    return  new JobExecutionListener() {
        @Override
        public void beforeJob(JobExecution jobExecution) {
        }

        @Override
        public void afterJob(JobExecution jobExecution) {
        System.out.println("All task done");
        }
    };
}

}
