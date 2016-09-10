package org.home.notation.polish.item;

import java.math.BigDecimal;

public interface Item {
    default BigDecimal applyFunction(BigDecimal firstItem, BigDecimal secondItem) {
        throw new UnsupportedOperationException("The item does not support this operation.");
    }
}
