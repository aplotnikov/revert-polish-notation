package org.home.notation.polish;

import lombok.AllArgsConstructor;
import org.home.notation.polish.item.Item;
import org.home.notation.polish.item.Operation;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public class OperationConfiguration {
    private final Map<String, Operation> operations;

    public Item find(String operation) {
        return operations.computeIfAbsent(operation, name -> {
            throw new IllegalArgumentException("The operation 'my operation' was not configured. Please, check configuration.");
        });
    }

    public static OperationConfiguration of(Operation... operations) {
        return new OperationConfiguration(
                Stream.of(operations).collect(toMap(Operation::getSymbol, operation -> operation))
        );
    }
}
