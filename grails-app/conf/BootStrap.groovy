import com.grailsrocks.taxonomy.Taxonomy

class BootStrap {

     def init = { servletContext ->
     	def taxonomy1 = new Taxonomy(name: "Cuisine")
		taxonomy1.save()
	 }
     def destroy = {
     }
} 