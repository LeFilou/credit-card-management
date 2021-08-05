package org.melsif.creditcardmanagement.coreapi;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CreditCardSummary implements Serializable {

    @Id
    private String creditCardId;
    private BigDecimal limitAssigned;
    private BigDecimal usedLimit = ZERO;

    public CreditCardSummary(String creditCardId) {
        this.creditCardId = creditCardId;
        limitAssigned = ZERO;
    }

    public void assignLimit(BigDecimal limit) {
        this.limitAssigned = limit;
    }

    //private CreditCardSummary() {} // for hibernate
}
