package com.app

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ChildrenServiceSpec extends Specification {

    ChildrenService childrenService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Children(...).save(flush: true, failOnError: true)
        //new Children(...).save(flush: true, failOnError: true)
        //Children children = new Children(...).save(flush: true, failOnError: true)
        //new Children(...).save(flush: true, failOnError: true)
        //new Children(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //children.id
    }

    void "test get"() {
        setupData()

        expect:
        childrenService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Children> childrenList = childrenService.list(max: 2, offset: 2)

        then:
        childrenList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        childrenService.count() == 5
    }

    void "test delete"() {
        Long childrenId = setupData()

        expect:
        childrenService.count() == 5

        when:
        childrenService.delete(childrenId)
        sessionFactory.currentSession.flush()

        then:
        childrenService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Children children = new Children()
        childrenService.save(children)

        then:
        children.id != null
    }
}
