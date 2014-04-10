package com.froad.bank.framework.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 树
 * @param <T> 树的节点数据源
 */
public class GenericTree<T> {

    private GenericTreeNode<T> root; // 根节点

    /**
     * 所有节点的个数（包括孙子节点，也包括自身）
     */
    public int getAllNodesCount() {
        int ret = 0;

        if (root != null) {
            // +1 为当前节点
            ret = getAllNodesCount(root) + 1;
        }

        return ret;
    }

    /**
     * 所有子节点的个数（包括孙子节点，但不包括自身）
     * 
     * @param node
     *            当前节点
     */
    private int getAllNodesCount(GenericTreeNode<T> node) {
        int ret = node.getChildrenCount();

        for (GenericTreeNode<T> child : node.getChildren()) {
            ret += getAllNodesCount(child);
        }

        return ret;
    }

    public GenericTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(GenericTreeNode<T> root) {
        this.root = root;
    }

    /**
     * 检索指定节点数据
     * 
     * @param data
     *            检索节点数据
     */
    public GenericTreeNode<T> search(T data) {
        GenericTreeNode<T> ret = null;

        if (root != null) {
            ret = search(root, data);
        }

        return ret;
    }

    /**
     * 检索指定节点数据
     * 
     * @param node
     *            待检索节点
     * @param data
     *            检索节点数据
     */
    private GenericTreeNode<T> search(GenericTreeNode<T> node, T data) {
        GenericTreeNode<T> ret = null;

        if (node.getData().equals(data)) {
            // 当前节点就是所需检索的节点
            ret = node;
        } else if (!node.isLeaf()) {
            int i = 0;

            while (ret == null && i < node.getChildrenCount()) {
                ret = search(node.getChildAt(i), data);
                i++;
            }
        }

        return ret;
    }

    public boolean contains(T data) {
        return search(data) != null;
    }

    /**
     * 判断是否是空树
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 前序遍历
     */
    public ArrayList<GenericTreeNode<T>> build() {
        ArrayList<GenericTreeNode<T>> ret = new ArrayList<GenericTreeNode<T>>();

        if (root != null) {
            buildPreOrder(root, ret);
        }

        return ret;
    }

    /**
     * 前序遍历
     */
    private void buildPreOrder(GenericTreeNode<T> node,
            List<GenericTreeNode<T>> traversal) {
        traversal.add(node);
        
        for (GenericTreeNode<T> child : node.getChildren()) {
            buildPreOrder(child, traversal);
        }
    }
    
    @Override
    public String toString() {
        if(root != null) {
            return  build().toString();
        }

        return null;
    }

}
