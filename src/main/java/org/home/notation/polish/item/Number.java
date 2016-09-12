package org.home.notation.polish.item;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@AllArgsConstructor
@Value
public class Number implements Item {
    private final BigDecimal value;

    public static Number of(double value) {
        return new Number(BigDecimal.valueOf(value));
    }
}
