package com.app

import grails.gorm.services.Service

@Service(Children)
interface ChildrenService {

    Children get(Serializable id)

    List<Children> list(Map args)

    Long count()

    void delete(Serializable id)

    Children save(Children children)

}