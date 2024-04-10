package com.app

class Parent {

    String familyName
    String givenName
    String middleName
    
    static hasMany = [children: Children, grandChildren: GrandChildren]

    static constraints = {
    }
}
