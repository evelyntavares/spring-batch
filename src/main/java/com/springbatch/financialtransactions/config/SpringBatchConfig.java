package com.springbatch.financialtransactions.config;

import com.springbatch.financialtransactions.domain.Transaction;
import com.springbatch.financialtransactions.listener.FinancialTransactionJobListener;
import com.springbatch.financialtransactions.processor.FinancialTransactionProcessor;
import com.springbatch.financialtransactions.writer.FinancialTransactionWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SpringBatchConfig {

  @Bean
  public Step createFinancialTransactionStep(
      JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      FlatFileItemReader<Transaction> financialTransactionReader,
      FinancialTransactionProcessor financialTransactionProcessor,
      FinancialTransactionWriter financialTransactionWriter) {

    return new StepBuilder("transactionStep", jobRepository)
        .<Transaction, Transaction>chunk(10, transactionManager)
        .reader(financialTransactionReader)
        .processor(financialTransactionProcessor)
        .writer(financialTransactionWriter)
        .build();
  }

  @Bean
  public Job createJob(
      JobRepository jobRepository, Step step, FinancialTransactionJobListener jobListener) {
    return new JobBuilder("job", jobRepository).start(step).listener(jobListener).build();
  }
}
