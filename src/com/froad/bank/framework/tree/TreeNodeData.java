package com.froad.bank.framework.tree;


/**
 * 树的节点数据源
 */
public class TreeNodeData {

    private String id;
    private String title;
    
    public TreeNodeData() {
        
    }
    
    public TreeNodeData(String id, String title) {
        this();
        this.id = id;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof TreeNodeData)) {
            return false;
        }

        TreeNodeData other = (TreeNodeData) o;

        if (id.equals(other.getId()) 
                && title.equals(other.getTitle())) {
            return true;
        }
        
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TreeNodeData [id=" + id + ", title=" + title + "]";
    }
    

}
