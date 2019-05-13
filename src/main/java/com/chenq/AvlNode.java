package com.chenq;

public class AvlNode {

    public AvlNode(Integer element) {
        this(element, null, null);
    }

    public AvlNode(Integer element, AvlNode left, AvlNode right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    Integer element;

    AvlNode left;
    AvlNode right;
    int height = 1;

    public static  int height(AvlNode t) {
        if (t == null) {
            return 0;
        } else {
            return t.height;
        }
    }

    public AvlNode insert(Integer x, AvlNode t) {
        if (t == null) {
            return new AvlNode(x);
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {
            //equal  do nothing

        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return balance(t);

    }

    public static final int ALLOWED_IMBALANCED = 1;

    private AvlNode balance(AvlNode t) {
        if (t == null) {
            return t;
        }
        if (height(t.left) == height(t.right)) {
            return t;
        }
        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCED) {
            //如果左子树比右子树高1以上
            //如果左左子树比左右字数高 left肯定不为空
            if (height(t.left.left) >= height(t.left.right)) {
                return rotateWithLeftChild(t);
            } else {
                return doubleWithLeftChild(t);
            }
        } else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCED) {
            if (height(t.right.left) - height(t.right.right) > ALLOWED_IMBALANCED) {
                return doubleWithRightChild(t);
            } else {
                //右右子树比较高 那么和左左子树是相似的
                return totateWithRightChild(t);
            }
        } else {
            //说明没超过限度  啥也不做
            return t;
        }

    }

    private AvlNode doubleWithRightChild(AvlNode t) {
        //右左子树比较高 这里和左右子树差不多
        AvlNode k1 = t;
        AvlNode k3, k4, k5;
        k3 = t.right;
        k4 = k3.left;
        k5 = k4.left;
        k1.right = k5;
        k3.left = k4.right;
        k4.left = k1;
        k4.right = k3;
        resize(k1);
        resize(k4);
        resize(k4);
        return k4;
    }

    private void resize(AvlNode a) {
        a.height = Math.max(height(a.left), height(a.right)) + 1;
    }

    private AvlNode totateWithRightChild(AvlNode t) {
        AvlNode k1=t;
        AvlNode k3 = t.right;
        k1.right=k3.left;
        k3.left=k1;
        resize(k1);
        resize(k3);
        return k3;
    }

    private AvlNode doubleWithLeftChild(AvlNode t) {
        //t的左右子树深度比较深
        AvlNode k1, k2, k3, k4;
        k1 = t;
        k2 = t.left;
        k4 = k2.right;
        k2.right = null;
        k4.left = k2;
        k1.left = k4.right;
        k4.right = k1;

        resize(k2);
        resize(k1);
        return k4;
    }

    private AvlNode rotateWithLeftChild(AvlNode t) {
        //左子树高于右子树
        //左左子树比左右子树高 那么很简单了
        AvlNode k1, k2, k3, k4;
        k1 = t;
        k2 = t.left;
        k3 = t.left.left;
        k4 = t.left.right;
        k2.right = k1;
        k1.left = k4;
        resize(k1);
        resize(k2);
        return k2;

    }



}
