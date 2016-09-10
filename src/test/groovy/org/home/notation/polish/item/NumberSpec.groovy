package org.home.notation.polish.item

import static java.math.BigDecimal.ONE

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class NumberSpec extends Specification {
    void "UnsupportedOperationException should be thrown when an user tries to call 'applyFunction' method"() {
        given:
        Number number = Number.of("1")

        when:
        number.applyFunction(ONE, ONE)

        then:
        UnsupportedOperationException exception = thrown(UnsupportedOperationException)
        and:
        exception.message == 'The item does not support this operation.'
    }

    void "NumberFormatException should be thrown when input value (#value) has incorrect format"() {
        when:
        Number.of(value)

        then:
        thrown(NumberFormatException)

        where:
        value << ["q", "!", "#"]
    }
}
