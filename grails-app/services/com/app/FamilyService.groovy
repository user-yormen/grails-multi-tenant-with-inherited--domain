package com.app

import grails.gorm.services.Service

@Service(Family)
interface FamilyService {

    Family get(Serializable id)

    List<Family> list(Map args)

    Long count()

    void delete(Serializable id)

    Family save(Family family)

}