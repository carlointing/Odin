package org.synergis.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mortbay.util.ajax.JSONObjectConvertor;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.TreeItem;

public class JSONTreeUtil {

	/**
	 * Creates a TreeItem hierarchy from a JSONArray. The JSONArray should be an array
	 * of JSONObjects with the following keys:
	 * 		<li>a parent key that contains the parent object information</li>
	 * 		<li>an id key that serves as a unique identifier for the JSONObjects</li>
	 * 		<li>a tree label key whose value will be displayed in the corresponding 
	 * 			TreeItem</li>
	 * <br></br>
	 * If the JSONArray contains anything that is not a JSONObject, or doesn't have 
	 * the keys above, they will be ignored.
	 * 
	 * A List of root TreeItem objects (meaning, TreeItem objects without parents) 
	 * is returned.  
	 * 
	 * @param jsonItems
	 * @param parentProperty
	 * @param idProperty
	 * @param treeLabelProperty
	 * @return 
	 */
	public static List<TreeItem> createTreeItemFromJSONArray(
			JSONArray jsonItems, String parentProperty, String idProperty,
			String treeLabelProperty) {
		if (jsonItems == null || parentProperty == null || idProperty == null
				|| treeLabelProperty == null) {
			throw new IllegalArgumentException("All parameters are required!");
		}		
		
		List<TreeItem> rootTreeItems = new ArrayList<TreeItem>();

		Map<String, IntermediateTreeItem> treeItemMap = new HashMap<String, IntermediateTreeItem>();
		
		for (int i = 0; i < jsonItems.size(); i++) {
			JSONObject currentItem = jsonItems.get(i).isObject();
			if (currentItem != null) {
				
				String parentId;

				if (currentItem.get(parentProperty).isNull() != null) {
					parentId = null;
				} else if (currentItem.get(parentProperty).isObject() != null) {
					JSONObject parentObject = currentItem.get(parentProperty)
							.isObject();
					parentId = Double.toString(parentObject.get(idProperty)
							.isNumber().doubleValue());
				} else {
					parentId = null;
				}
				
				String taxonName = currentItem.get(treeLabelProperty)
						.isString().stringValue();
				
				IntermediateTreeItem treeItem = new IntermediateTreeItem(
						new TreeItem(taxonName), parentId);
				String taxonId = Double.toString(currentItem.get(idProperty)
						.isNumber().doubleValue());				
				treeItemMap.put(taxonId, treeItem);
			}
		}
		
		for (Map.Entry<String, IntermediateTreeItem> entry : treeItemMap
				.entrySet()) {
			
			if (entry.getValue().getTreeParentId() != null) {
				TreeItem parent = treeItemMap.get(
						entry.getValue().getTreeParentId()).getTreeItem();				
				if (parent != null) {					
					parent.addItem(entry.getValue().getTreeItem());
				} else {					
					rootTreeItems.add(entry.getValue().getTreeItem());
				}
			} else {				
				rootTreeItems.add(entry.getValue().getTreeItem());
			}
		}

		return rootTreeItems;
	}
	
	public static String convertJSONValueToString(JSONValue value) {
		
		String stringValue;
		
		if (value.isBoolean() != null) {
			stringValue = value.isBoolean().toString();
		} else if (value.isNumber() != null) {
			stringValue = Double.toString(value.isNumber().doubleValue());
		} else if (value.isString() != null) {
			stringValue = value.isString().stringValue();
		} else if (value.isObject() != null) {
			stringValue = value.isObject().toString();
		} else if (value.isArray() != null) {
			StringBuilder arrayString = new StringBuilder();						
		}
		
		
		return null;
	}
	
	
	private static class IntermediateTreeItem {
		private TreeItem treeItem;
		private String treeParentId;

		IntermediateTreeItem(TreeItem treeItem, String treeParentId) {
			this.treeItem = treeItem;

			if (treeParentId != null && !treeParentId.isEmpty()) {
				this.treeParentId = treeParentId;
			}
		}

		public TreeItem getTreeItem() {
			return treeItem;
		}

		public String getTreeParentId() {
			return treeParentId;
		}
	}

}
