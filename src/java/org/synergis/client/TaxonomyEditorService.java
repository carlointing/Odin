package org.synergis.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface TaxonomyEditorService extends RemoteService {
    java.util.List listTaxonomies();
}
