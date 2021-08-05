package org.melsif.creditcardmanagement.coreapi;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class WithdrawEvent {
    String creditCardId;
    BigDecimal moneyWithdrawn;
}