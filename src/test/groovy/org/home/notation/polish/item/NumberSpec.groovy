package org.home.notation.polish.item

import static java.math.BigDecimal.ONE

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class NumberSpec extends Specification {
    void "UnsupportedOperationException should be thrown when an user tries to call 'applyFunction' method and value is #value"() {
        given:
        Number number = Number.of(value as double)

        when:
        number.applyFunction(ONE, ONE)

        then:
        UnsupportedOperationException exception = thrown(UnsupportedOperationException)
        and:
        exception.message == 'The item does not support this operation.'

        where:
        value << [1, 1.1]
    }
}
