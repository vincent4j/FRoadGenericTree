package com.froad.bank.framework.tree;

import org.json.JSONObject;

/**
 * 双树的解析器
 */
public class DoubleTreeParser extends BaseTreeParser {

    private final static String TREE_KEY_ROOT_LEFT = "jrzs"; // 左边树的根节点 Key
    private final static String TREE_KEY_ROOT_RIGHT = "ydyh"; // 右边树的根节点 Key

    private GenericTree<TreeNodeData> treeLeft; // 左边树的根节点
    private GenericTree<TreeNodeData> treeRight; // 右边树的根节点
    
    private static DoubleTreeParser instance = new DoubleTreeParser();
    
    private DoubleTreeParser() {
        treeLeft = new GenericTree<TreeNodeData>();
        treeRight = new GenericTree<TreeNodeData>();
    }
    
    public static DoubleTreeParser getInstance() {
        return instance;
    }

    @Override
    public void parse(JSONObject tree) {
        // 构建左边树
        parseRootNode(treeLeft, tree, TREE_KEY_ROOT_LEFT);
        parsePreOrder(treeLeft.getRoot(), getRootChildren(tree, TREE_KEY_ROOT_LEFT));
        
        // 构建右边树
        parseRootNode(treeRight, tree, TREE_KEY_ROOT_RIGHT);
        parsePreOrder(treeRight.getRoot(), getRootChildren(tree, TREE_KEY_ROOT_RIGHT));
    }

    public GenericTree<TreeNodeData> getTreeLeft() {
        return treeLeft;
    }

    public void setTreeLeft(GenericTree<TreeNodeData> treeLeft) {
        this.treeLeft = treeLeft;
    }

    public GenericTree<TreeNodeData> getTreeRight() {
        return treeRight;
    }

    public void setTreeRight(GenericTree<TreeNodeData> treeRight) {
        this.treeRight = treeRight;
    }

}
