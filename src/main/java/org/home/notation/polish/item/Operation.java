package org.home.notation.polish.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Operation implements Item {
    private final String symbol;
}
