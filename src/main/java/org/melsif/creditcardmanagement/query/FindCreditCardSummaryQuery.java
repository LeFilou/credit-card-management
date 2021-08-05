package org.melsif.creditcardmanagement.query;

import lombok.Value;

import java.util.UUID;

@Value
public class FindCreditCardSummaryQuery {
    UUID creditCardId;
}
