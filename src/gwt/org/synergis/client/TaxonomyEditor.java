package org.synergis.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TaxonomyEditor implements EntryPoint {

	private TaxonomyEditorServiceAsync taxonomyEditorService;
	
	private ListBox taxonomyListBox;
	
	private Tree taxonomyTree;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		taxonomyListBox = new ListBox();
		
		// TODO: refactor this out
		taxonomyEditorService = (TaxonomyEditorServiceAsync) GWT
				.create(TaxonomyEditorService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget) taxonomyEditorService;
		String moduleRelativeUrl = GWT.getModuleBaseURL() + "rpc";
		endPoint.setServiceEntryPoint(moduleRelativeUrl);
						
		taxonomyEditorService.listTaxonomies(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				// log the error
				// notify user of error
			}

			@Override
			public void onSuccess(String taxonomies) {
				JSONValue value = JSONParser.parse(taxonomies);							
				JSONArray taxonomyList = value.isArray();				
				if (taxonomyList != null) {
					initializeTaxonomyList(taxonomyList);
				}
			}
		});
		
		
		
		
		RootPanel.get("taxonomyContainer").add(taxonomyListBox);
		
	}
	
	private void initializeTaxonomyList(JSONArray taxonomyList) {
		JSONObject taxonomy;
		
		for (int i = 0; i < taxonomyList.size(); i++) {
			taxonomy = taxonomyList.get(i).isObject();
			JSONString name = taxonomy.get("name").isString();			
			taxonomyListBox.addItem(name.stringValue());
		}
	}
	
}
