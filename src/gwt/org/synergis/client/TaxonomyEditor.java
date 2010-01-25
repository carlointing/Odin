package org.synergis.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TaxonomyEditor implements EntryPoint {

	private TaxonomyEditorServiceAsync taxonomyEditorService;

	private ListBox taxonomyListBox;

	private Button createTaxonomyButton;

	private Button deleteTaxonomyButton;	

	private PopupPanel createTaxonomyPopup;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		initializeTaxonomyEditorService();

		taxonomyListBox = new ListBox();
		refreshTaxonomyListBox();

		initializeCreateTaxonomyPopup();
		initializeCreateTaxonomyButton();

		initializeDeleteTaxonomyButton();

		RootPanel rootPanel = RootPanel.get("taxonomyContainer");
		rootPanel.add(taxonomyListBox);
		rootPanel.add(createTaxonomyButton);
		rootPanel.add(deleteTaxonomyButton);
	}

	private void initializeDeleteTaxonomyButton() {
		deleteTaxonomyButton = new Button("Delete Taxonomy");
		deleteTaxonomyButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final String taxonomy = taxonomyListBox
						.getItemText(taxonomyListBox.getSelectedIndex());

				if (taxonomy == null || taxonomy.isEmpty()) {
					Window.alert("No Taxonomy has been selected!");
				} else {
					boolean answer = Window
							.confirm("Are you sure you want to delete "
									+ taxonomy);
					if (answer) {
						taxonomyEditorService.deleteTaxonomy(taxonomy,
								new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
										Window
												.alert("Error encountered while deleting "
														+ taxonomy + "!");

									}

									@Override
									public void onSuccess(Void result) {
										refreshTaxonomyListBox();
										Window.alert(taxonomy
												+ " has been deleted.");
									}

								});
					}
				}
			}

		});
	}

	private void initializeCreateTaxonomyButton() {
		createTaxonomyButton = new Button("Create New Taxonomy");
		createTaxonomyButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createTaxonomyPopup
						.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
							public void setPosition(int offsetWidth,
									int offsetHeight) {
								int left = (Window.getClientWidth() - offsetWidth) / 3;
								int top = (Window.getClientHeight() - offsetHeight) / 3;
								createTaxonomyPopup.setPopupPosition(left, top);
							}
						});
			}

		});
	}

	private void refreshTaxonomyListBox() {
		taxonomyListBox.clear();
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
					for (String item : convertJSONArrayToList(taxonomyList)) {
						taxonomyListBox.addItem(item);
					}
				}
			}

		});
	}

	private List<String> convertJSONArrayToList(JSONArray taxonomyList) {
		JSONObject taxonomy;
		List<String> items = new ArrayList<String>();

		for (int i = 0; i < taxonomyList.size(); i++) {
			taxonomy = taxonomyList.get(i).isObject();
			JSONString name = taxonomy.get("name").isString();
			items.add(name.stringValue());
		}

		return items;
	}

	private void initializeTaxonomyEditorService() {
		taxonomyEditorService = (TaxonomyEditorServiceAsync) GWT
				.create(TaxonomyEditorService.class);
		ServiceDefTarget endPoint = (ServiceDefTarget) taxonomyEditorService;
		String moduleRelativeUrl = GWT.getModuleBaseURL() + "rpc";
		endPoint.setServiceEntryPoint(moduleRelativeUrl);
	}

	private void initializeCreateTaxonomyPopup() {
		createTaxonomyPopup = new PopupPanel();

		Label popupText = new Label("Enter name of new Taxonomy");
		final TextBox createTaxonomyTextBox = new TextBox();
		Button okButton = new Button("Ok");
		Button cancelButton = new Button("Cancel");
		
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);		
		
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.add(popupText);
		mainPanel.add(createTaxonomyTextBox);
		mainPanel.add(buttonPanel);

		createTaxonomyPopup.add(mainPanel);
		createTaxonomyPopup.setModal(true);
		createTaxonomyPopup.setGlassEnabled(true);
		
		okButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				taxonomyEditorService.createTaxonomy(createTaxonomyTextBox
						.getText(), new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error creating taxonomy!");
					}

					@Override
					public void onSuccess(Void result) {
						createTaxonomyPopup.hide();
						refreshTaxonomyListBox();
					}

				});

			}
		});

		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createTaxonomyPopup.hide();
			}
		});
	}
}
