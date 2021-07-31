package org.melsif.creditcardmanagement.coreapi

import org.axonframework.commandhandling.RoutingKey
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.*

data class CreateCreditCardCommand(@RoutingKey val creditCardId: UUID)
data class AssignLimitCommand(@TargetAggregateIdentifier val creditCardId: UUID, val initialLimit: BigDecimal)
data class WithdrawCommand(@TargetAggregateIdentifier val creditCardId: UUID, val moneyWithdrawn: BigDecimal)

data class CreditCardCreatedEvent(val creditCardId: UUID)
data class LimitAssignedEvent(val creditCardId: UUID, val initialAmount: BigDecimal)
data class WithdrawEvent(val creditCardId: UUID, val moneyWithdrawn: BigDecimal)