package org.home.notation.polish

import static java.math.BigDecimal.ONE

import org.home.notation.polish.item.Operation
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.util.function.BiFunction

@Unroll
class CalculatorSpec extends Specification {
    @Shared
    OperationConfiguration operationConfiguration = OperationConfiguration.of(
            Operation.builder()
                    .symbol('+')
                    .function({ BigDecimal first, BigDecimal second -> first + second } as BiFunction<BigDecimal, BigDecimal, BigDecimal>)
                    .build(),
            Operation.builder()
                    .symbol('-')
                    .function({ BigDecimal first, BigDecimal second -> first - second } as BiFunction<BigDecimal, BigDecimal, BigDecimal>)
                    .build(),
            Operation.builder()
                    .symbol('*')
                    .function({ BigDecimal first, BigDecimal second -> first * second } as BiFunction<BigDecimal, BigDecimal, BigDecimal>)
                    .build(),
            Operation.builder()
                    .symbol('/')
                    .function({ BigDecimal first, BigDecimal second -> first / second } as BiFunction<BigDecimal, BigDecimal, BigDecimal>)
                    .build()
    )
    @Subject
    Calculator calculator = new Calculator()

    void "calculator should return #expectedValue when notation is '#notationText'"() {
        given:
        Notation notation = Notation.of(notationText, ' ', operationConfiguration)

        when:
        BigDecimal result = calculator.calculate(notation)

        then:
        result == expectedValue

        where:
        notationText                || expectedValue
        '1 2 +'                     || BigDecimal.valueOf(3)
        '2 1 -'                     || ONE
        '1 2 -'                     || BigDecimal.valueOf(-1)
        '2 2 *'                     || BigDecimal.valueOf(4)
        '4 2 /'                     || BigDecimal.valueOf(2)
        '8 2 5 * + 1 3 2 * + 4 - /' || BigDecimal.valueOf(6)
    }

    void "calculator should throw NoSuchElementException when '#notationText' is incorrect"() {
        given:
        Notation notation = Notation.of(notationText, ' ', operationConfiguration)

        when:
        calculator.calculate(notation)

        then:
        thrown(NoSuchElementException)

        where:
        notationText << ['1 +', '1 1 + +']
    }
}
