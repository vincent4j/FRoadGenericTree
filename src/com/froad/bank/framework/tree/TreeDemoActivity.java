package com.froad.bank.framework.tree;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

/**
 * Tree 使用演示
 */
public class TreeDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new TreeThread().start();
    }

    class TreeThread extends Thread {

        @Override
        public void run() {
            JSONObject treeJson = null;
            
            try {
                treeJson = new JSONObject(readFileFromPhone("081003json.txt", TreeDemoActivity.this));
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            /**「单树结构」 begin */
            // 只需调用方法 parse 即可将 Json 解析成树结构
            SingleTreeParser.getInstance().parse(treeJson);
            // 获取树
            GenericTree<TreeNodeData> tree = SingleTreeParser.getInstance().getTree();
            System.out.println(tree.toString());
            
            TreeNodeData targetNodeData = new TreeNodeData("A1", "账户管理");
            System.out.println("A1 == " + tree.search(targetNodeData));
            /**「单树结构」end */
            
            /**「双树结构」begin */
            // 只需调用方法 parse 即可将 Json 解析成树结构
            DoubleTreeParser.getInstance().parse(treeJson);
            // 获取左右树
            GenericTree<TreeNodeData> treeLeft = DoubleTreeParser.getInstance().getTreeLeft();
            System.out.println(treeLeft.toString());
            System.out.println("treeLeft.getAllNodesCount() = " + treeLeft.getAllNodesCount());
            GenericTree<TreeNodeData> treeRight = DoubleTreeParser.getInstance().getTreeRight();
            System.out.println(treeRight.toString());
            /**「双树结构」end */
            
            GenericTreeNode<TreeNodeData> treeNode = new GenericTreeNode<TreeNodeData>();
            TreeNodeData nodeData = new TreeNodeData();
            
            // 通过节点数据源反向查找到对应的节点
            tree.search(nodeData);
            
            // 树中是否包括指定节点
            tree.contains(nodeData);
            
            // 树的所有节点个数（包括孩子的孩子节点）
            tree.getAllNodesCount();
            
            // 判断节点是否是叶子节点
            treeNode.isLeaf();
            
            // 查找当前节点的孩子节点（不包括孩子的孩子节点）
            treeNode.getChildren();
            
            // 当前节点的孩子节点个数（不包括孩子的孩子节点）
            treeNode.getChildrenCount();
            
            // 查找当前几点的第 1 个孩子节点
            // treeNode.getChildAt(1);
            
            // 查找当前节点的父节点
            treeNode.getParent();
            
            // 判断两个节点是否是相同节点
            treeNode.equals(new GenericTreeNode<TreeNodeData>());
        }

    }
    
    public static String readFileFromPhone(String fileName, Context context) throws Exception {
        AssetManager am = context.getAssets();
        return new String(readStream(am.open(fileName)));
    }
    
    public static byte[] readStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while( (len=inStream.read(buffer)) != -1){
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

}
