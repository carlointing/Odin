package org.synergis.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TaxonomyEditorServiceAsync {
    void listTaxonomies(AsyncCallback<String> callback);
    void deleteTaxonomy(String taxonomyName, AsyncCallback<Void> callback);
    void createTaxonomy(String taxonomyName, AsyncCallback<Void> callback);
}
