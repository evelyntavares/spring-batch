package com.springbatch.financialtransactions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FINANCIAL_TRANSACTIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinancialTransaction {

  @Id @Column private String transactionId;

  @Column private LocalDateTime transactionDate;

  @Column private String sourceAccount;

  @Column private String destinationAccount;

  @Column private BigDecimal amount;

  @Column private String currency;

  @Column private boolean isSuspicious;

  @Column private LocalDateTime createdAt;
}
