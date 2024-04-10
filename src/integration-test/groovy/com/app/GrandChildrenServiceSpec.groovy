package com.app

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class GrandChildrenServiceSpec extends Specification {

    GrandChildrenService grandChildrenService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new GrandChildren(...).save(flush: true, failOnError: true)
        //new GrandChildren(...).save(flush: true, failOnError: true)
        //GrandChildren grandChildren = new GrandChildren(...).save(flush: true, failOnError: true)
        //new GrandChildren(...).save(flush: true, failOnError: true)
        //new GrandChildren(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //grandChildren.id
    }

    void "test get"() {
        setupData()

        expect:
        grandChildrenService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<GrandChildren> grandChildrenList = grandChildrenService.list(max: 2, offset: 2)

        then:
        grandChildrenList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        grandChildrenService.count() == 5
    }

    void "test delete"() {
        Long grandChildrenId = setupData()

        expect:
        grandChildrenService.count() == 5

        when:
        grandChildrenService.delete(grandChildrenId)
        sessionFactory.currentSession.flush()

        then:
        grandChildrenService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        GrandChildren grandChildren = new GrandChildren()
        grandChildrenService.save(grandChildren)

        then:
        grandChildren.id != null
    }
}
