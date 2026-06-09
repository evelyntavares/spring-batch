package com.springbatch.financialtransactions.writer;

import com.springbatch.financialtransactions.domain.Transaction;
import com.springbatch.financialtransactions.entity.FinancialTransaction;
import com.springbatch.financialtransactions.repository.FinancialTransactionRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FinancialTransactionWriter implements ItemWriter<Transaction> {

  private final FinancialTransactionRepository financialTransactionRepository;

  @Override
  public void write(@NonNull Chunk<? extends Transaction> chunk) throws Exception {

    List<FinancialTransaction> transactions =
        chunk.getItems().stream().map(this::toEntity).toList();

    financialTransactionRepository.saveAll(transactions);
  }

  public RepositoryItemWriter<FinancialTransaction> writeToDatabase() {
    return new RepositoryItemWriterBuilder<FinancialTransaction>()
        .repository(financialTransactionRepository)
        .methodName("save")
        .build();
  }

  private FinancialTransaction toEntity(Transaction financialTransaction) {
    FinancialTransaction entity = new FinancialTransaction();
    entity.setTransactionId(financialTransaction.getTransactionId());
    entity.setTransactionDate(financialTransaction.getTransactionDate());
    entity.setSourceAccount(financialTransaction.getSourceAccount());
    entity.setDestinationAccount(financialTransaction.getDestinationAccount());
    entity.setAmount(financialTransaction.getAmount());
    entity.setCurrency(financialTransaction.getCurrency());
    entity.setCreatedAt(LocalDateTime.now());
    return entity;
  }
}
