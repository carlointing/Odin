package org.synergis

class RestaurantController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ restaurantInstanceList: Restaurant.list( params ), restaurantInstanceTotal: Restaurant.count() ]
    }

    def show = {
        def restaurantInstance = Restaurant.get( params.id )

        if(!restaurantInstance) {
            flash.message = "Restaurant not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ restaurantInstance : restaurantInstance ] }
    }

    def delete = {
        def restaurantInstance = Restaurant.get( params.id )
        if(restaurantInstance) {
            try {
                restaurantInstance.delete(flush:true)
                flash.message = "Restaurant ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Restaurant ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Restaurant not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def restaurantInstance = Restaurant.get( params.id )

        if(!restaurantInstance) {
            flash.message = "Restaurant not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ restaurantInstance : restaurantInstance ]
        }
    }

    def update = {
        def restaurantInstance = Restaurant.get( params.id )
        if(restaurantInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(restaurantInstance.version > version) {
                    
                    restaurantInstance.errors.rejectValue("version", "restaurant.optimistic.locking.failure", "Another user has updated this Restaurant while you were editing.")
                    render(view:'edit',model:[restaurantInstance:restaurantInstance])
                    return
                }
            }
            restaurantInstance.properties = params
            if(!restaurantInstance.hasErrors() && restaurantInstance.save()) {
                flash.message = "Restaurant ${params.id} updated"
                redirect(action:show,id:restaurantInstance.id)
            }
            else {
                render(view:'edit',model:[restaurantInstance:restaurantInstance])
            }
        }
        else {
            flash.message = "Restaurant not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def restaurantInstance = new Restaurant()
        restaurantInstance.properties = params
        return ['restaurantInstance':restaurantInstance]
    }

    def save = {
        def restaurantInstance = new Restaurant(params)
        if(!restaurantInstance.hasErrors() && restaurantInstance.save()) {
            flash.message = "Restaurant ${restaurantInstance.id} created"
            redirect(action:show,id:restaurantInstance.id)
        }
        else {
            render(view:'create',model:[restaurantInstance:restaurantInstance])
        }
    }
}
