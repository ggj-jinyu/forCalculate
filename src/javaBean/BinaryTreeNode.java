package javaBean;

import java.util.Objects;

public class BinaryTreeNode {
    private Object data;  //数据
    private BinaryTreeNode leftChild;  //左孩子
    private BinaryTreeNode rightChild; //右孩子

    public BinaryTreeNode() { }

    public BinaryTreeNode(Object data) {
        this.data = data;
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

    /**
     * 比较大小的方法
     * @param node 被比较的节点
     * @return this>node: true
     */
    public boolean max(BinaryTreeNode node){
        if(this.data instanceof Fraction && node.getData() instanceof Fraction){
            return ((Fraction) this.data).max((Fraction) node.getData());
        }else return false;
    }

    @Override
    public String toString() {
        if(data instanceof String) return (String) data;
        return data.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryTreeNode node = (BinaryTreeNode) o;

        if (!Objects.equals(data, node.data)) return false;
        if (!Objects.equals(leftChild, node.leftChild)) return false;
        return Objects.equals(rightChild, node.rightChild);
    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (leftChild != null ? leftChild.hashCode() : 0);
        result = 31 * result + (rightChild != null ? rightChild.hashCode() : 0);
        return result;
    }
}
