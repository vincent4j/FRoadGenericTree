package com.froad.bank.framework.tree;

import java.util.ArrayList;

/**
 * 树节点
 * @param <T> 树节点的数据源
 */
public class GenericTreeNode<T> {

    private T data; // 节点数据
    private GenericTreeNode<T> parent; // 父节点
    private ArrayList<GenericTreeNode<T>> children; // 子节点

    public GenericTreeNode() {
        children = new ArrayList<GenericTreeNode<T>>();
    }

    public GenericTreeNode(T data) {
        this();
        setData(data);
    }
    
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 获取所有孩子节点的列表
     */
    public ArrayList<GenericTreeNode<T>> getChildren() {
        return children;
    }

    /**
     * 孩子节点个数
     */
    public int getChildrenCount() {
        return getChildren().size();
    }

    /**
     * 获取指定位置的孩子节点
     * 
     * @param index
     *            指定的位置
     */
    public GenericTreeNode<T> getChildAt(int index) {
        return children.get(index);
    }

    /**
     * 是否是叶子节点
     */
    public boolean isLeaf() {
        return getChildrenCount() < 1;
    }

    /**
     * 添加孩子节点
     * 
     * @param children
     *            孩子节点
     */
    public void setChildren(ArrayList<GenericTreeNode<T>> children) {
        if (children == null) {
            new IllegalArgumentException(
                    "The children will been added is null.");
        }

        for (int i = 0; i < children.size(); i++) {
            GenericTreeNode<T> child = children.get(i);

            if (child == null) {
                new IllegalArgumentException("The child index of " + i
                        + " will been added is null.");
            }

            child.parent = this;
        }

        this.children = children;
    }

    /**
     * 添加单一的孩子节点到最后位置
     * 
     * @param child
     *            单一孩子节点
     */
    public void addChild(GenericTreeNode<T> child) {
        addChildAt(children.size(), child);
    }

    /**
     * 添加单一的孩子节点到指定位置
     * 
     * @param index
     *            添加的位置
     * @param child
     *            单一孩子节点
     */
    public void addChildAt(int index, GenericTreeNode<T> child) {
        if (child == null) {
            new IllegalArgumentException("The child will been added is null.");
        }

        child.parent = this;
        children.add(index, child);
    }

    public void setParent(GenericTreeNode<T> parent) {
        this.parent = parent;
    }

    /**
     * 查找父节点
     */
    public GenericTreeNode<T> getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "[" + (data == null ? "null" : data.toString() + children.toString()) + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof GenericTreeNode<?>)) {
            return false;
        }

        GenericTreeNode<?> other = (GenericTreeNode<?>) o;
        
        // 判断标准：只要数据源相同就认为相等，不比较父节点和子节点
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }

        return true;
    }

}
