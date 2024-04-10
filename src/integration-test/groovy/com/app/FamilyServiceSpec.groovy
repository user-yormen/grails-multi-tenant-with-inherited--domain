package com.app

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FamilyServiceSpec extends Specification {

    FamilyService familyService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Family(...).save(flush: true, failOnError: true)
        //new Family(...).save(flush: true, failOnError: true)
        //Family family = new Family(...).save(flush: true, failOnError: true)
        //new Family(...).save(flush: true, failOnError: true)
        //new Family(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //family.id
    }

    void "test get"() {
        setupData()

        expect:
        familyService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Family> familyList = familyService.list(max: 2, offset: 2)

        then:
        familyList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        familyService.count() == 5
    }

    void "test delete"() {
        Long familyId = setupData()

        expect:
        familyService.count() == 5

        when:
        familyService.delete(familyId)
        sessionFactory.currentSession.flush()

        then:
        familyService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Family family = new Family()
        familyService.save(family)

        then:
        family.id != null
    }
}
