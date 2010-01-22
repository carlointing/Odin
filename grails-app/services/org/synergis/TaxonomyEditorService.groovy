package org.synergis

import com.grailsrocks.taxonomy.Taxonomy
import com.grailsrocks.taxonomy.TaxonomyService

class TaxonomyEditorService {
	
	static expose = ['gwt:org.synergis.client']
	
    boolean transactional = true
	
	def taxonomyService
	
    String listTaxonomies() {
		def taxonomyList = Taxonomy.list()
		return taxonomyList.encodeAsJSON()
    }
	
	def deleteTaxonomy(String taxonomyName) {
		// delete all related Taxons
		
		// delete Taxonomy
	}
	
	def createTaxonomy(String taxonomyName) {
		def taxonomy = new Taxonomy(name:taxonomyName)
		taxonomy.save()
	}
}
