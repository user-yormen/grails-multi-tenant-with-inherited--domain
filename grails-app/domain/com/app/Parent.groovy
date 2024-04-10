package com.app

import grails.gorm.MultiTenant
import grails.gorm.multitenancy.CurrentTenant

@CurrentTenant
class Parent implements MultiTenant<Parent>{
//class Parent{

    ParentType parentType

    String familyName
    String givenName
    String middleName

    static belongsTo = [family: Family]

    static constraints = {
    }
}

enum ParentType{
    WOMAN,
    MAN
}