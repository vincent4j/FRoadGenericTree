package com.froad.bank.framework.tree;

import org.json.JSONObject;

/**
 * 单一树的解析器
 */
public class SingleTreeParser extends BaseTreeParser {
    
    private final static String TREE_KEY_ROOT = "ydyh"; // 树的根节点 Key
    
    private GenericTree<TreeNodeData> tree; // 树的根节点

    private static SingleTreeParser instance = new SingleTreeParser();
    
    private SingleTreeParser() {
        tree = new GenericTree<TreeNodeData>();
    }
    
    public static SingleTreeParser getInstance() {
        return instance;
    }

    @Override
    public void parse(JSONObject tree) {
        // 构建树
        parseRootNode(this.tree, tree, TREE_KEY_ROOT);
        parsePreOrder(this.tree.getRoot(), getRootChildren(tree, TREE_KEY_ROOT));
    }

    public GenericTree<TreeNodeData> getTree() {
        return tree;
    }

    public void setTree(GenericTree<TreeNodeData> tree) {
        this.tree = tree;
    }

}
