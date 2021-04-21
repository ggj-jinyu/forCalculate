package javaBean;

public class BinaryTreeNode {
    private Object data;  //数据
    private BinaryTreeNode leftChild;  //左孩子
    private BinaryTreeNode rightChild; //右孩子

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(Object data) {
        this.data = data;
    }

    public BinaryTreeNode(Object data, BinaryTreeNode leftChild) {
        this.data = data;
        this.leftChild = leftChild;
    }

    public BinaryTreeNode(Object data, BinaryTreeNode leftChild, BinaryTreeNode rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BinaryTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public boolean max(BinaryTreeNode node){
        if(this.data instanceof Fraction && node.getData() instanceof Fraction){
            return ((Fraction) this.data).max((Fraction) node.getData());
        }else return false;
    }
}
