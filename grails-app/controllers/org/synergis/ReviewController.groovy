package org.synergis

class ReviewController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ reviewInstanceList: Review.list( params ), reviewInstanceTotal: Review.count() ]
    }

    def show = {
        def reviewInstance = Review.get( params.id )

        if(!reviewInstance) {
            flash.message = "Review not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ reviewInstance : reviewInstance ] }
    }

    def delete = {
        def reviewInstance = Review.get( params.id )
        if(reviewInstance) {
            try {
                reviewInstance.delete(flush:true)
                flash.message = "Review ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Review ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Review not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def reviewInstance = Review.get( params.id )

        if(!reviewInstance) {
            flash.message = "Review not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ reviewInstance : reviewInstance ]
        }
    }

    def update = {
        def reviewInstance = Review.get( params.id )
        if(reviewInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(reviewInstance.version > version) {
                    
                    reviewInstance.errors.rejectValue("version", "review.optimistic.locking.failure", "Another user has updated this Review while you were editing.")
                    render(view:'edit',model:[reviewInstance:reviewInstance])
                    return
                }
            }
            reviewInstance.properties = params
            if(!reviewInstance.hasErrors() && reviewInstance.save()) {
                flash.message = "Review ${params.id} updated"
                redirect(action:show,id:reviewInstance.id)
            }
            else {
                render(view:'edit',model:[reviewInstance:reviewInstance])
            }
        }
        else {
            flash.message = "Review not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def reviewInstance = new Review()
        reviewInstance.properties = params
        return ['reviewInstance':reviewInstance]
    }

    def save = {
        def reviewInstance = new Review(params)
        if(!reviewInstance.hasErrors() && reviewInstance.save()) {
            flash.message = "Review ${reviewInstance.id} created"
            redirect(action:show,id:reviewInstance.id)
        }
        else {
            render(view:'create',model:[reviewInstance:reviewInstance])
        }
    }
}
