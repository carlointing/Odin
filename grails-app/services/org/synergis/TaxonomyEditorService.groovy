package org.synergis

import com.grailsrocks.taxonomy.Taxonomy
import com.grailsrocks.taxonomy.TaxonomyService

class TaxonomyEditorService {
	
	static expose = ['gwt:org.synergis.client']
	
    boolean transactional = true
	
	def taxonomyService
	
    String listTaxonomies() {
		def taxonomyList = Taxonomy.findAllByNameNotEqual(TaxonomyService.GLOBAL_TAXONOMY_NAME)
		return taxonomyList.encodeAsJSON()
    }
	
	def deleteTaxonomy(String taxonomyName) {
		// TODO: need to check whether TaxonLinks should be cleaned up as well
		
		// deleting the Taxonomy should cascade to all Taxons		
		def results = Taxonomy.findAllByName(taxonomyName)
		for (Taxonomy taxonomy : results) {
			taxonomy.delete()
		}
	}
	
	def createTaxonomy(String taxonomyName) {
		def taxonomy = new Taxonomy(name:taxonomyName)
		taxonomy.save()
	}
}
