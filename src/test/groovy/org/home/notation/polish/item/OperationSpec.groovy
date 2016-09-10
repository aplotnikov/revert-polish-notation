package org.home.notation.polish.item

import static java.math.BigDecimal.ONE
import static java.math.BigDecimal.TEN

import spock.lang.Specification
import spock.lang.Unroll

import java.util.function.BiFunction

@Unroll
class OperationSpec extends Specification {
    void "configured operation should be applied when operation is #symbol"() {
        given:
        Operation operation = Operation.builder()
                .symbol(symbol)
                .function(function as BiFunction<BigDecimal, BigDecimal, BigDecimal>)
                .build()

        when:
        BigDecimal result = operation.applyFunction(TEN, ONE)

        then:
        result == expectedResult

        where:
        symbol | function                                                  || expectedResult
        '+'    | { BigDecimal first, BigDecimal second -> first + second } || BigDecimal.valueOf(11)
        '-'    | { BigDecimal first, BigDecimal second -> first - second } || BigDecimal.valueOf(9)
    }
}
