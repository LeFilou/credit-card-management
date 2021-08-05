package org.melsif.creditcardmanagement.ui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssignLimitRequest {
    private BigDecimal limitAssigned;
}
