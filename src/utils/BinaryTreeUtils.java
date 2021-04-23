package utils;

import javaBean.BinaryTreeNode;

public class BinaryTreeUtils {
    private BinaryTreeUtils() { }

    /**
     * 二叉树的清空：
     * 首先提供一个清空以某个节点为根节点的子树的方法，既递归地删除每个节点；
     * 接着提供一个删除树的方法，直接通过第一种方法删除到根节点即可
     */
    //清除某个子树的所有节点
    public static void clear(BinaryTreeNode node){
        if(node!=null){
            clear(node.getLeftChild());
            clear(node.getRightChild());
            node = null; //删除节点
        }
    }

    /**
     * 求二叉树的高度：
     * 首先要一种获取以某个节点为子树的高度的方法，使用递归调用。
     * 如果一个节点为空，那么这个节点肯定是一颗空树，高度为0；
     * 如果不为空，那么我们要遍历地比较它的左子树高度和右子树高度，
     *     高的一个为这个子树的最大高度，然后加上自己本身的高度就是了
     * 获取二叉树的高度，只需要调用第一种方法，即传入根节点
     */
    //获取以某节点为子树的高度
    public static int height(BinaryTreeNode node){
        if(node==null){
            return 0; //递归结束，空子树高度为0
        }else{
            //递归获取左子树高度
            int l = height(node.getLeftChild());
            //递归获取右子树高度
            int r = height(node.getRightChild());
            //高度应该算更高的一边，（+1是因为要算上自身这一层）
            return l>r? (l+1):(r+1);
        }
    }

    /**
     * 求二叉树的节点数：
     * 求节点数时，我们看看获取某个节点为子树的节点数的实现。
     * 首先节点为空，则个数肯定为0；
     * 如果不为空，那就算上这个节点之后继续递归所有左右子树的子节点数，
     *    全部相加就是以所给节点为根的子树的节点数
     * 如果求二叉树的节点数，则输入根节点即可
     */
    public static int size(BinaryTreeNode node){
        if(node==null){
            return 0;  //如果节点为空，则返回节点数为0
        }else{
            //计算本节点 所以要+1
            //递归获取左子树节点数和右子树节点数，最终相加
            return 1+size(node.getLeftChild())+size(node.getRightChild());
        }
    }

    //node节点在subTree子树中的父节点
    public static BinaryTreeNode getParent(BinaryTreeNode subTree,BinaryTreeNode node){
        if(subTree==null){
            return null;   //如果是空子树，则没有父节点
        }
        if(subTree.getLeftChild()==node || subTree.getRightChild() == node){
            return subTree;   //如果子树的根节点的左右孩子之一是待查节点，则返回子树的根节点
        }
        BinaryTreeNode parent;
        if(getParent(subTree.getLeftChild(),node)!=null){
            parent = getParent(subTree.getLeftChild(),node);
            return parent;
        }else{
            //递归右子树
            return getParent(subTree.getRightChild(),node);
        }
    }

    //给某个节点插入左节点
    public static void insertLeft(BinaryTreeNode parent,BinaryTreeNode newNode){
        parent.setLeftChild(newNode);
    }
    //给某个节点插入右节点
    public static void insertRight(BinaryTreeNode parent,BinaryTreeNode newNode){
        parent.setRightChild(newNode);
    }


    //先序遍历
    public static void preOrder(BinaryTreeNode node){
        if(node!=null){
            System.out.println(node.getData()); //先访问根节点
            preOrder(node.getLeftChild());  //先根遍历左子树
            preOrder(node.getRightChild());  //先根遍历右子树
        }
    }

    //中序遍历
    public static void inOrder(BinaryTreeNode node){
        if(node!=null){
            inOrder(node.getLeftChild());  //中根遍历左子树
            System.out.println(node);    //访问根节点
            inOrder(node.getRightChild());  //中根遍历右子树
        }
    }

    //后序遍历
    public static void postOrder(BinaryTreeNode node){
        if(node!=null){
            postOrder(node.getLeftChild());  //后根遍历左子树
            postOrder(node.getRightChild());  //后根遍历右子树
            System.out.println(node);   //访问根节点
        }
    }

    //比较二叉树是否相等
    public static boolean isSameTree(BinaryTreeNode p,BinaryTreeNode q) {
        //两棵二叉树均为null
        if (p == null && q == null) {
            return true;
        }
        //其中只有一棵树为null
        if (p == null || q == null) {
            return false;
        }
        //节点值不相等
        if (!p.getData().equals(q.getData())) {
            return false;
        }
        //两棵树不为null,且节点值相等，分别比较该节点的左子树、右子树
        return isSameTree(p.getLeftChild(), q.getLeftChild())
                && isSameTree(p.getRightChild(), q.getRightChild());
    }
}
