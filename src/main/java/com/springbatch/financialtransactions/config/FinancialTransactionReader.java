package com.springbatch.financialtransactions.config;

import com.springbatch.financialtransactions.domain.Transaction;
import java.time.LocalDateTime;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FinancialTransactionReader {

  private final String FILE_LOCATION = "financial_transactions.csv";

  @Bean
  public FlatFileItemReader<Transaction> readFile() {
    System.out.println("RESOURCE = " + FILE_LOCATION);
    return new FlatFileItemReaderBuilder<Transaction>()
        .resource(new ClassPathResource(FILE_LOCATION))
        .name("csvReader")
        .linesToSkip(1)
        .lineMapper(createLineMapper())
        .build();
  }

  private LineMapper<Transaction> createLineMapper() {
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter(",");
    lineTokenizer.setStrict(false);
    lineTokenizer.setNames(
        "transaction_id",
        "transaction_date",
        "source_account",
        "destination_account",
        "amount",
        "currency");

    DefaultLineMapper<Transaction> lineMapper = new DefaultLineMapper<>();
    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(
        fieldSet -> {
          Transaction t = new Transaction();
          t.setTransactionId(fieldSet.readString("transaction_id"));
          t.setTransactionDate(LocalDateTime.parse(fieldSet.readString("transaction_date")));
          t.setSourceAccount(fieldSet.readString("source_account"));
          t.setDestinationAccount(fieldSet.readString("destination_account"));
          t.setAmount(fieldSet.readBigDecimal("amount"));
          t.setCurrency(fieldSet.readString("currency"));

          return t;
        });

    return lineMapper;
  }
}
