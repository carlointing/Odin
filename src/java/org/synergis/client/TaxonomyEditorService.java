package org.synergis.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface TaxonomyEditorService extends RemoteService {
    String listTaxonomies();    
    void deleteTaxonomy(String taxonomyName);
    void createTaxonomy(String taxonomyName);
    String findTaxonsByTaxonomy(String taxonomy);
}
