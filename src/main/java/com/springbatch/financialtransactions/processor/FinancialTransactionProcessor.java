package com.springbatch.financialtransactions.processor;

import static java.util.Objects.isNull;

import com.springbatch.financialtransactions.domain.Transaction;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class FinancialTransactionProcessor implements ItemProcessor<Transaction, Transaction> {

  private final Set<String> processedFinancialTransactions = new HashSet<>();
  @Getter private int duplicateTransactionCount;
  @Getter private int invalidAmountCount;
  @Getter private int invalidAccountCount;
  @Getter private int suspiciousCount;

  @Override
  public Transaction process(@NonNull Transaction transaction) throws Exception {

    if (isDuplicate(transaction)) {
      duplicateTransactionCount++;
      return null;
    }

    if (isAmountInvalid(transaction)) {
      invalidAmountCount++;
      return null;
    }

    validateTransactionAccount(transaction);
    flagSuspiciousTransaction(transaction);
    return transaction;
  }

  private boolean isDuplicate(Transaction transaction) {
    return !processedFinancialTransactions.add(transaction.getTransactionId());
  }

  private boolean isAmountInvalid(Transaction transaction) {
    return isNull(transaction.getAmount())
        || transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0;
  }

  private void validateTransactionAccount(Transaction transaction) throws Exception {
    if (transaction.getSourceAccount().isBlank() || transaction.getDestinationAccount().isBlank()) {
      invalidAccountCount++;
    }
  }

  private void flagSuspiciousTransaction(Transaction transaction) {
    if (transaction.getAmount().compareTo(new BigDecimal(10000)) > 0) {
      transaction.setSuspicious(true);
      suspiciousCount++;
    }
  }
}
