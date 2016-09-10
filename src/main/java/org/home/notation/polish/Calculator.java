package org.home.notation.polish;

import org.home.notation.polish.item.Item;
import org.home.notation.polish.item.Number;
import org.home.notation.polish.item.Operation;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {
    private final Deque<BigDecimal> numbers = new ArrayDeque<>();

    public BigDecimal calculate(Notation notation) {
        numbers.clear();

        while (notation.hasNext()) {
            Item item = notation.next();

            if (item instanceof Number) {
                numbers.addFirst(((Number) item).getValue());
            } else {
                Operation operation = (Operation) item;

                BigDecimal secondNumber = numbers.removeFirst();
                BigDecimal firstNumber = numbers.removeFirst();

                numbers.addFirst(operation.applyFunction(firstNumber, secondNumber));
            }
        }

        return numbers.removeFirst();
    }
}
