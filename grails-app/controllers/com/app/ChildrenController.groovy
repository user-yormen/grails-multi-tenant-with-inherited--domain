package com.app

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ChildrenController {

    ChildrenService childrenService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond childrenService.list(params), model:[childrenCount: childrenService.count()]
    }

    def show(Long id) {
        respond childrenService.get(id)
    }

    def create() {
        respond new Children(params)
    }

    def save(Children children) {
        if (children == null) {
            notFound()
            return
        }

        try {
            childrenService.save(children)
        } catch (ValidationException e) {
            respond children.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'children.label', default: 'Children'), children.id])
                redirect children
            }
            '*' { respond children, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond childrenService.get(id)
    }

    def update(Children children) {
        if (children == null) {
            notFound()
            return
        }

        try {
            childrenService.save(children)
        } catch (ValidationException e) {
            respond children.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'children.label', default: 'Children'), children.id])
                redirect children
            }
            '*'{ respond children, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        childrenService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'children.label', default: 'Children'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'children.label', default: 'Children'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
