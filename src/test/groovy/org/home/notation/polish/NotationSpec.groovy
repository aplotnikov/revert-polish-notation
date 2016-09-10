package org.home.notation.polish

import org.home.notation.polish.item.Item
import org.home.notation.polish.item.Number
import org.home.notation.polish.item.Operation
import spock.lang.Shared
import spock.lang.Specification

class NotationSpec extends Specification {
    @Shared
    OperationConfiguration operationConfiguration = OperationConfiguration.of(
            Operation.builder().symbol('+').build()
    )

    void "notation should return next number item"() {
        when:
        Notation notation = Notation.of('1', ' ', operationConfiguration)

        then:
        notation.hasNext()

        when:
        Item nextItem = notation.next()

        then:
        with(nextItem) {
            it instanceof Number
            value == 1
        }
        and:
        !notation.hasNext()
    }

    void "notation should return next operation item"() {
        when:
        Notation notation = Notation.of('+', ' ', operationConfiguration)

        then:
        notation.hasNext()

        when:
        Item nextItem = notation.next()

        then:
        with(nextItem) {
            it instanceof Operation
            symbol == '+'
        }
        and:
        !notation.hasNext()
    }

    void "notation should throw IllegalStateException when it has no items"() {
        when:
        Notation notation = Notation.of('1', ' ', operationConfiguration)

        then:
        notation.hasNext()

        when:
        Item nextItem = notation.next()

        then:
        nextItem != null
        and:
        !notation.hasNext()

        when:
        notation.next()

        then:
        IllegalStateException exception = thrown(IllegalStateException)
        and:
        exception.message == 'Notation does not contain any another items'
    }
}
