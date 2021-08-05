package org.melsif.creditcardmanagement.coreapi;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

import static java.math.BigDecimal.ZERO;

@Entity
@Getter
public class CreditCardSummary {

    @Id
    private UUID creditCardId;
    private BigDecimal limit;
    private BigDecimal usedLimit = ZERO;

    public CreditCardSummary(UUID creditCardId) {
        this.creditCardId = creditCardId;
        limit = ZERO;
        usedLimit = ZERO;
    }

    public void assignLimit(BigDecimal limit) {
        this.limit = limit;
    }

    private CreditCardSummary() {} // for hibernate
}
