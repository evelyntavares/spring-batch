package com.springbatch.financialtransactions.repository;

import com.springbatch.financialtransactions.entity.FinancialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialTransactionRepository
    extends JpaRepository<FinancialTransaction, String> {}
