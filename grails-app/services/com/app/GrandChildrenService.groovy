package com.app

import grails.gorm.services.Service

@Service(GrandChildren)
interface GrandChildrenService {

    GrandChildren get(Serializable id)

    List<GrandChildren> list(Map args)

    Long count()

    void delete(Serializable id)

    GrandChildren save(GrandChildren grandChildren)

}