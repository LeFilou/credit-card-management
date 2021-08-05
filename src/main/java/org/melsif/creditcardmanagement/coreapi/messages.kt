package org.melsif.creditcardmanagement.coreapi

import org.axonframework.commandhandling.RoutingKey
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.*

data class CreateCreditCardCommand(@RoutingKey val creditCardId: String)
data class AssignLimitCommand(@TargetAggregateIdentifier val creditCardId: String, val initialLimit: BigDecimal)
data class WithdrawCommand(@TargetAggregateIdentifier val creditCardId: String, val moneyWithdrawn: BigDecimal)

data class CreditCardCreatedEvent(val creditCardId: String)
data class LimitAssignedEvent(val creditCardId: String, val initialAmount: BigDecimal)
data class WithdrawEvent(val creditCardId: String, val moneyWithdrawn: BigDecimal)