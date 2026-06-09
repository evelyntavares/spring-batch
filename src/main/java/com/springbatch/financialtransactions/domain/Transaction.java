package com.springbatch.financialtransactions.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
  private String transactionId;

  private LocalDateTime transactionDate;

  private String sourceAccount;

  private String destinationAccount;

  private BigDecimal amount;

  private String currency;

  private boolean isSuspicious;
}
