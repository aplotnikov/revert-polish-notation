package org.home.notation.polish

import org.home.notation.polish.item.Item
import org.home.notation.polish.item.Number
import org.home.notation.polish.item.Operation
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class NotationSpec extends Specification {
    @Shared
    OperationConfiguration operationConfiguration = OperationConfiguration.of(
            Operation.builder().symbol('+').build()
    )

    void "NullPointerException should be thrown when operation configuration is null"() {
        when:
        Notation.of('1', ' ', null)

        then:
        thrown(NullPointerException)
    }

    void "notation should have two items when splitter is '#splitter'"() {
        given:
        Notation notation = Notation.of(notationText, splitter, operationConfiguration)

        when:
        Item firstItem = notation.next()

        then:
        with(firstItem) {
            it instanceof Number
            value == 1
        }
        and:
        notation.hasNext()

        when:
        Item secondItem = notation.next()

        then:
        with(secondItem) {
            it instanceof Operation
            symbol == '+'
        }
        and:
        !notation.hasNext()

        where:
        notationText | splitter
        '1 +'        | ' '
        '1,+'        | ','
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
