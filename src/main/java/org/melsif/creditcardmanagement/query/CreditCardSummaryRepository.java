package org.melsif.creditcardmanagement.query;

import org.melsif.creditcardmanagement.coreapi.CreditCardSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditCardSummaryRepository extends JpaRepository<CreditCardSummary, UUID> {
}
