package linkedList.singlelinkdeList;

/**
 * @author shkstart
 * @create 2020-04-04 14:16
 * 链表存储的人
 */
public class HeroNode {
    public int id; // 英雄编号
    public String name; // 英雄名
    public HeroNode next; // 指针

    // 带参构造器
    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // 重写toString方法

    @Override
    public String toString() {
        return "HeroNode{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
