package org.home.notation.polish.item

import static java.math.BigDecimal.ONE

import spock.lang.Specification

class NumberSpec extends Specification {
    void "UnsupportedOperationException should be thrown when an user tries to call 'applyFunction' method"() {
        given:
        Number number = new Number()

        when:
        number.applyFunction(ONE, ONE)

        then:
        UnsupportedOperationException exception = thrown(UnsupportedOperationException)
        and:
        exception.message == 'The item does not support this operation.'
    }
}
