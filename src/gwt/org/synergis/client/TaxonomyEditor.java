package org.synergis.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TaxonomyEditor implements EntryPoint {

	private TaxonomyEditorServiceAsync taxonomyEditorService;
	private Label testLabel;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		testLabel = new Label();
		
		taxonomyEditorService = (TaxonomyEditorServiceAsync) GWT
				.create(TaxonomyEditorService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget) taxonomyEditorService;
		String moduleRelativeUrl = GWT.getModuleBaseURL() + "rpc";
		endPoint.setServiceEntryPoint(moduleRelativeUrl);
		
		taxonomyEditorService.listTaxonomies(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				testLabel.setText("Foo!:" + caught.getMessage());
				
			}

			@Override
			public void onSuccess(String taxonomies) {
				JSONValue value = JSONParser.parse(taxonomies);
				testLabel.setText("Bar!: " + value.toString());			
				
			}
		});
		
		RootPanel.get("taxonomyContainer").add(testLabel);
		
	}
}
