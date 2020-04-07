package linkedList.singlelinkdeList.doubleLinkedList;

/**
 * @author shkstart
 * @create 2020-04-06 10:08
 * 这个类是双向链表的节点类
 */
public class Node {
    public int id;
    public String name;
    Node next;      // next为指向后面的指针
    Node pre;       // pre为指向前面的指针

    // 带参的构造方法
    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // 重写toString

    @Override
    public String toString() {
        return "Node{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
