package specialinew

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class MyJobSpec extends Specification implements DomainUnitTest<MyJob> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
