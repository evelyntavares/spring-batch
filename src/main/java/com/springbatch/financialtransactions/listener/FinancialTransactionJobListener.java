package com.springbatch.financialtransactions.listener;

import com.springbatch.financialtransactions.processor.FinancialTransactionProcessor;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FinancialTransactionJobListener implements JobExecutionListener {
  private static final Logger log = LoggerFactory.getLogger(FinancialTransactionJobListener.class);

  private final FinancialTransactionProcessor processor;

  @Override
  public void afterJob(JobExecution jobExecution) {

    log.info(
        """

        ===== JOB SUMMARY =====
        Duplicates: {}
        Invalid Accounts: {}
        Invalid Amounts: {}
        Suspicious Transactions: {}
        =======================
        """,
        processor.getDuplicateTransactionCount(),
        processor.getInvalidAccountCount(),
        processor.getInvalidAmountCount(),
        processor.getSuspiciousCount());
  }
}
