package com.chenq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : goldgreat
 * @Description :
 * @Date :  2019/5/13 10:02
 */
public class RbTree {

    private RBNode root;
    //    节点是红色或黑色。
//    根是黑色。
//    所有叶子都是黑色（叶子是NIL节点）。
//    每个红色节点必须有两个黑色的子节点。（从每个叶子到根的所有路径上不能有两个连续的红色节点。）
//    从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点。


    private static boolean RED = false;
    private static boolean BLACK = true;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public class RBNode {
        public RBNode(int value, boolean color) {
            this.value = value;
            this.color = color;
        }

        int value;
        boolean color;
        RBNode left, right, parent;

        public RBNode getUncleNode(){
            if(parent!=null){
                if(parent.getParent()!=null){
                    RBNode uncle=parent.getParent().getLeft()==parent?parent.getParent().getRight():parent.getParent().getLeft();
                    return uncle;
                }
            }
            return null;
        }
    }

    public void insert(Integer value, RBNode top) {
        if (top == null) {
            root = new RBNode(value, RED);
        } else {
            int compareResult = value.compareTo(top.getValue());
            if (compareResult > 0) {
                if (top.getRight() != null) {
                    insert(value, top.right);
                } else {
                    RBNode rbNode = new RBNode(value, RED, null, null, top);
                    top.setRight(rbNode);
                }
            } else if (compareResult < 0) {
                if (top.getLeft() != null) {
                    insert(value, top.left);
                } else {
                    RBNode rbNode = new RBNode(value, RED, null, null, top);
                    top.setLeft(rbNode);
                }
            }
        }

    }

    void insertCase(RBNode rbNode) {
        if (rbNode.getParent() == null) {
            rbNode.color = BLACK;
            root = rbNode;
            return;
        } else {
            if(rbNode.getParent().isColor()==BLACK){
                return ;
            }else{
                //父节点是红色

            }
        }
    }


}