package org.synergis

import com.grailsrocks.taxonomy.Taxonomy

class TaxonomyEditorService {
	
	static expose = ['gwt:org.synergis.client']
	
    boolean transactional = true

    String listTaxonomies() {
		def taxonomyList = Taxonomy.list()
		return taxonomyList.encodeAsJSON()
    }
}
