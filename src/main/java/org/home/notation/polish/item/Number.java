package org.home.notation.polish.item;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Number implements Item {
    private final BigDecimal value;

    public static Number of(String value) {
        return new Number(BigDecimal.valueOf(Float.valueOf(value)));
    }
}
