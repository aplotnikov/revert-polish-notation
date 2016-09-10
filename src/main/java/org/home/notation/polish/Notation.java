package org.home.notation.polish;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.home.notation.polish.item.Item;
import org.home.notation.polish.item.Number;

import java.util.Iterator;

import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@Log4j
public class Notation implements Iterator<Item> {
    private final OperationConfiguration operationConfiguration;
    private final String[] items;
    private int currentItemIndex;

    @Override
    public boolean hasNext() {
        return currentItemIndex < items.length;
    }

    @Override
    public Item next() {
        if (currentItemIndex >= items.length) {
            throw new IllegalStateException("Notation does not contain any another items");
        }

        String item = items[currentItemIndex++];
        try {
            return Number.of(Float.valueOf(item));
        } catch (NumberFormatException exception) {
            log.debug("The item " + item + " was not converted to number.");
        }

        return operationConfiguration.find(item);
    }

    public static Notation of(String notation, String splitter, OperationConfiguration operationConfiguration) {
        return new Notation(
                requireNonNull(operationConfiguration),
                notation.split(splitter),
                0
        );
    }
}
