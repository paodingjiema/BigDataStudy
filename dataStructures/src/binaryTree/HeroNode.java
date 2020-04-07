package binaryTree;

/**
 * @author shkstart
 * @create 2020-03-31 12:12
 * 该类做为各个节点的类型
 */
public class HeroNode {
    int node;   // 英雄编号
    String name;    // 英雄名
    HeroNode left;  // 左节点,默认为空
    HeroNode right; // 右节点，默认为空

    // 带参构造
    public HeroNode(int node, String name) {
        this.node = node;
        this.name = name;
    }

    // 重写toString方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "node=" + node +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
