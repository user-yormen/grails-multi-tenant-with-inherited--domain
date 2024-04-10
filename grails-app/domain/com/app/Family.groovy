package com.app

import grails.gorm.MultiTenant

class Family implements MultiTenant<Family>{
    String familyName

    @Override
    String toString() {
//        return super.toString()
        return familyName
    }

    static constraints = {
    }
}
