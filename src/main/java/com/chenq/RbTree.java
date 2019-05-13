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

        public RBNode getUncleNode() {
            if (parent != null) {
                if (parent.getParent() != null) {
                    RBNode uncle = parent.getParent().getLeft() == parent ? parent.getParent().getRight() : parent.getParent().getLeft();
                    return uncle;
                }
            }
            return null;
        }

        public RBNode getGrandPa() {
            if (parent != null) {
                return parent.getParent();
            }
            return null;
        }

        public RBNode getBrother() {
            if (getParent() != null) {
                return getParent().getLeft() == this ? this : parent.getRight();
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

    void insertCase1(RBNode rbNode) {
        if (rbNode.getParent() == null) {
            rbNode.color = BLACK;
            root = rbNode;
        } else {
            insertCase2(rbNode);
        }
    }

    private void insertCase2(RBNode rbNode) {
        if (rbNode.getParent().isColor() == BLACK) {
            return;
        } else {
            insertCase3(rbNode);
        }
    }

    private void insertCase3(RBNode rbNode) {
        if (rbNode.getParent().isColor() == BLACK) {
            return;
        } else {
            //父节点是红色
            if (rbNode.getUncleNode() != null && rbNode.getUncleNode().isColor() == RED) {
                //如果父节点和叔父节点都是红色的
                //那么把祖父节点置为红色  父节点和叔父节点都置为黑色
                rbNode.getParent().setColor(BLACK);
                rbNode.getUncleNode().setColor(BLACK);
                rbNode.getGrandPa().setColor(RED);
                //但是祖父节点的父节点有可能是红色或者祖父节点有可能就是顶点
                insertCase1(rbNode.getGrandPa());
            } else {
                //叔父节点是黑色或者或者叔父节点不存在
                // 父节点是祖父节点的左节点 本节点是父节点的右子节点  那么进行一次左旋转
                if (rbNode.getParent() == rbNode.getGrandPa().getLeft()
                        && rbNode == rbNode.getParent().getRight()) {
                    //左旋转
                    totateLeft(rbNode.getParent());

                    rbNode = rbNode.left;
                } else if (rbNode.getParent() == rbNode.getGrandPa().getRight()
                        && rbNode == rbNode.getParent().getLeft()) {
                    //父节点是祖父节点的右子树 本身节点是祖父节点的左子树  那么右旋转
                    totateRight(rbNode.getParent());
                    rbNode = rbNode.right;
                }
                insert_case5(rbNode);
            }
        }
    }

    private void insert_case5(RBNode rbNode) {
        //右右都为红  或者左左都为红
        rbNode.parent.color = BLACK;
        rbNode.getGrandPa().color = RED;
        if (rbNode == rbNode.parent.left && rbNode.parent == rbNode.getGrandPa().getLeft()) {
            totateRight(rbNode.getGrandPa());
        } else if (rbNode == rbNode.parent.right &&
                rbNode.getGrandPa().right == rbNode.getParent()) {
            totateLeft(rbNode.getGrandPa());
        }
    }

    void totateRight(RBNode k2) {
        RBNode k3 = k2.left;
        k3.parent = k2.parent;
        RBNode k5 = k3.right;
        k3.left = k2;
        if (k5 != null) {
            k5.parent = k2;
        }
        k2.left = k5;
        k2.parent = k3;
    }

    void totateLeft(RBNode rbNode) {
        RBNode k1 = rbNode.getParent();
        RBNode k4 = rbNode.getRight();
        k1.setLeft(k4);
        k4.setParent(k1);
        rbNode.setParent(k4);
        //
        rbNode.setRight(k4.getLeft());
        if (k4.getLeft() != null) {
            k4.getLeft().setParent(rbNode);
        }
        k4.setLeft(rbNode);
    }

    void insertCase(RBNode rbNode) {
        insertCase1(rbNode);
    }

    void deleteOneChild(int a, RBNode node) {

    }



    RBNode findMin(RBNode a) {
        if (a.getLeft() != null) {
            return findMin(a.getLeft());
        } else {
            return a;
        }
    }

    public static RBNode findInNode(Integer a, RBNode node) {
        if (node == null) {
            return null;
        }
        int compareResult = a.compareTo(node.value);
        if (compareResult > 0) {
            return findInNode(a, node.right);
        } else if (compareResult < 0) {
            return findInNode(a, node.left);
        } else {
            return node;
        }
    }


}