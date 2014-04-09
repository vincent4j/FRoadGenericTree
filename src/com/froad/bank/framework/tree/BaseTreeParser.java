package com.froad.bank.framework.tree;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public abstract class BaseTreeParser {
    
    protected final static String TREE_KEY_DETAIL_ID = "id";
    protected final static  String TREE_KEY_DETAIL_TITLE = "title";
    protected final static String TREE_KEY_DETAIL_CHILDREN = "children";
    
    /**
     * 树解析
     * @param tree Json 格式的数据源
     */
    public abstract void parse(JSONObject tree);
    
    /**
     * 递归前序遍历构建树
     * 
     * @param rootNode
     *            当前树的根节点
     * @param children
     *            孩子节点（下挂的所有节点）
     */
    protected void parsePreOrder(GenericTreeNode<TreeNodeData> rootNode, JSONArray children) {
        if (rootNode == null 
                || rootNode.getData() == null
                || children == null) {
            return;
        }
        
        for (int i = 0; i < children.length(); i++) {
            JSONObject nodeChildJson = null; // 当前节点 rootNode 的孩子

            String dataId = null; // 当前节点 rootNode 的孩子的数据元素 id
            String dataTitle = null; // 当前节点 rootNode 的孩子的数据元素 title
            JSONArray dataChildren = null; // 当前节点 rootNode 的孩子的孩子
            
            try {
                nodeChildJson = children.getJSONObject(i);
                
                dataId = nodeChildJson.getString(TREE_KEY_DETAIL_ID);
                dataTitle = nodeChildJson.getString(TREE_KEY_DETAIL_TITLE);
                dataChildren = nodeChildJson.getJSONArray(TREE_KEY_DETAIL_CHILDREN);
            } catch (Exception e) {
                e.printStackTrace();
                new IllegalArgumentException("The source of tree " + children.toString() + "is invalid, please check it.");
            }
            
            TreeNodeData data = new TreeNodeData(dataId, dataTitle);
            GenericTreeNode<TreeNodeData> nodeChild = new GenericTreeNode<TreeNodeData>(data);
            rootNode.addChild(nodeChild);
            
            Log.d("Tree", "Child of tree is " + data.toString());

            if (dataChildren != null && dataChildren.length() > 0) {
                parsePreOrder(nodeChild, dataChildren);
            }
        }
    }
    
    /**
     * 构建树的根节点
     * @param tree 一颗新树，用于构建
     * @param treeJson 一颗完整的树对应的 Json 数据
     * @param jsonKey 树根节点的 Json Key
     */
    protected void parseRootNode(GenericTree<TreeNodeData> tree, JSONObject treeJson, String jsonKey) {
        if (treeJson == null 
                || jsonKey == null 
                || jsonKey.length() < 1) {
            return;
        }
        
        JSONObject rootValueJson = null; // root 对应的 json 值
        GenericTreeNode<TreeNodeData> node = null; // 树的根节点
        TreeNodeData data = null; // 树跟节点的数据源
        String dataId = null; // 树跟节点的数据元素 id
        String dataTitle = null; // 树跟节点的数据元素 title
        
        try {
            rootValueJson = treeJson.getJSONObject(jsonKey);
            dataId = rootValueJson.getString(TREE_KEY_DETAIL_ID);
            dataTitle = rootValueJson.getString(TREE_KEY_DETAIL_TITLE);
            
            data = new TreeNodeData(dataId, dataTitle);
            node = new GenericTreeNode<TreeNodeData>(data);
            
            tree.setRoot(node);
            
            Log.d("Tree", "Root of tree is " + node.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            new IllegalArgumentException("The source of tree " + treeJson.toString() + "is invalid, please check it.");
        }
    }
    
    /**
     * 构建树的一级子节点
     * @param tree 一颗完整的树对应的 Json 数据
     * @param key 树根节点的 Json Key
     * @return
     */
    protected JSONArray getRootChildren(JSONObject tree, String key) {
        if (tree == null 
                || key == null 
                || key.length() < 1) {
            return null;
        }
        
        JSONArray ret = null;
        JSONObject rootValueJson = null;
        
        try {
            rootValueJson = tree.getJSONObject(key);
            ret = rootValueJson.getJSONArray(TREE_KEY_DETAIL_CHILDREN);
        } catch (JSONException e) {
            e.printStackTrace();
            new IllegalArgumentException(
                    "The key[" + key + " or " + TREE_KEY_DETAIL_CHILDREN 
                        + "] of root tree is not exist, please check it.");
        }
        
        return ret;
    }

}
