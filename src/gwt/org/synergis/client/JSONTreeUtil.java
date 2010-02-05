package org.synergis.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.TreeItem;

public class JSONTreeUtil {

	/**
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
