package org.melsif.creditcardmanagement.coreapi;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class WithdrawEvent {
    UUID creditCardId;
    BigDecimal moneyWithdrawn;
}
