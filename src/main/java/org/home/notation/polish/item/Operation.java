package org.home.notation.polish.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import static lombok.AccessLevel.PRIVATE;

@Builder
@AllArgsConstructor(access = PRIVATE)
@ToString(exclude = {"function"})
@EqualsAndHashCode(exclude = {"function"})
public class Operation implements Item {
    @Getter
    private final String symbol;
    private final BiFunction<BigDecimal, BigDecimal, BigDecimal> function;

    @Override
    public BigDecimal applyFunction(BigDecimal firstItem, BigDecimal secondItem) {
        return function.apply(firstItem, secondItem);
    }
}
