package binaryTree;

import sun.management.jdp.JdpJmxPacket;

/**
 * @author shkstart
 * @create 2020-03-31 14:26
 * 二叉树的工具类
 */
public class BinaryTreeTools {
    // 前序遍历
    public void preOrder(HeroNode root){
        System.out.println(root);
        // 递归遍历左子树
        if(root.left != null){
            HeroNode temp;
            temp = root.left;
            preOrder(temp);
        }
        // 递归遍历右子树
        if(root.right != null){
            HeroNode temp;
            temp = root.right;
            preOrder(temp);
        }
    }

    // 中序遍历
    public void infixOrder(HeroNode root){
      // 递归输出左子树
      if(root.left != null){
          HeroNode temp = root.left;
          infixOrder(temp);
      }
      System.out.println(root);
      // 递归输出右子树
        if(root.right != null){
            HeroNode temp = root.right;
            infixOrder(temp);
        }
    }

    // 后序遍历
    public void epilOrder(HeroNode root){
        // 递归输出左子树
        if(root.left != null){
            HeroNode temp = root.left;
            epilOrder(temp);
        }

        // 递归输出右子树
        if(root.right != null){
            HeroNode temp = root.right;
            epilOrder(temp);
        }

        System.out.println(root);
    }
}
