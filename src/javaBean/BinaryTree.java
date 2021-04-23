package javaBean;

import utils.BinaryTreeUtils;

public class BinaryTree {
    private BinaryTreeNode root;

    public BinaryTree() {
    }

    public BinaryTree(BinaryTreeNode root) {
        this.root = root;
    }

    public BinaryTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    //清空树
    public void clear(){
        BinaryTreeUtils.clear(this.root);
    }

    //判断二叉树是否为空
    public boolean isEmpty(){
        return this.root == null;
    }

    //获取二叉树的高度
    public int height(){
        return BinaryTreeUtils.height(this.root);
    }

    //获取二叉树的节点数
    public int size(){
        return BinaryTreeUtils.size(this.root);
    }

    //查找node节点在二叉树root中的父节点
    public BinaryTreeNode getParent(BinaryTreeNode node){
        return (this.root==null||this.root==node)? null:BinaryTreeUtils.getParent(this.root,node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryTree that = (BinaryTree) o;

        return root != null ? BinaryTreeUtils.isSameTree(root,that.getRoot()) : that.root == null;
    }

    @Override
    public int hashCode() {
        return root != null ? root.hashCode() : 0;
    }
}

