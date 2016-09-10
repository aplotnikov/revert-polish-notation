package org.home.notation.polish

import org.home.notation.polish.item.Item
import org.home.notation.polish.item.Operation
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

@Unroll
class OperationConfigurationSpec extends Specification {
    @Subject
    OperationConfiguration configuration = OperationConfiguration.of(
            Operation.builder().symbol('+').build(),
            Operation.builder().symbol('-').build()
    )

    void "it should be possible to find configured '#operation' operation"() {
        when:
        Item find = configuration.find(operation)

        then:
        find.symbol == expectedName

        where:
        operation || expectedName
        '+'       || '+'
        '-'       || '-'
    }

    void "IllegalArgumentException should be thrown when an user tries to find not configured operation"() {
        when:
        configuration.find('my operation')

        then:
        IllegalArgumentException exception = thrown(IllegalArgumentException)
        and:
        exception.message == 'The operation \'my operation\' was not configured. Please, check configuration.'
    }
}
