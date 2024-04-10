package com.app

import grails.gorm.MultiTenant
import grails.gorm.multitenancy.CurrentTenant

@CurrentTenant
class Children extends Parent implements MultiTenant<Children> {
//class Children extends Parent {
    int childNumber

    Date dateOfBirth

    static constraints = {
        parentType nullable: false
    }
}
