import com.grailsrocks.taxonomy.Taxonomy
import com.grailsrocks.taxonomy.TaxonomyService

class BootStrap {
	
	 def taxonomyService
	
     def init = { servletContext ->
     	def taxonomy1 = new Taxonomy(name: "Cuisine")
		taxonomy1.save()
		
		taxonomyService.createTaxonomyPath("Asian,Thai","Cuisine")
		taxonomyService.createTaxonomyPath("Asian,Indian","Cuisine")
		taxonomyService.createTaxonomyPath("Western,French","Cuisine")
	 }
	
     def destroy = {
     }
} 