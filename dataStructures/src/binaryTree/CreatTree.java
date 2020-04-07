package binaryTree;

/**
 * @author shkstart
 * @create 2020-03-31 14:51
 * 用来建立一个二叉树,先不把这个类拿出去。就当测试类吧
 */
public class CreatTree {
    public static void main(String[] args){
        HeroNode root = new HeroNode(1,"林冲");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"李逵");
        HeroNode node4 = new HeroNode(4,"武松");
        HeroNode node5 = new HeroNode(5,"潘金莲");
        HeroNode node6 = new HeroNode(5,"松江");


        // 手动将节点挂起构成一课树
        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;

        // 调用工具类，前序遍历
        BinaryTreeTools brt = new BinaryTreeTools();

        // 前序输出二叉树
        brt.preOrder(root);
        System.out.println();
        // 中序输出二叉树
        brt.infixOrder(root);
        System.out.println();
        // 后序输出二叉树
        brt.epilOrder(root);
    }
}
