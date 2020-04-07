package linkedList.singlelinkdeList.doubleLinkedList;

/**
 * @author shkstart
 * @create 2020-04-06 10:36
 */
public class DoubleLinkdeListTest {
    public static void main(String[] args) {
        // 先创建一些节点
        Node node1 = new Node(1,"刘亦菲");
        Node node2 = new Node(2,"王祖贤");
        Node node3 = new Node(3,"林青霞");
        Node node4 = new Node(4,"邱淑贞");

        // 创建一个双链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        // 先输出下链表
        doubleLinkedList.show();
        System.out.println();

        // 将链表加入
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);
        doubleLinkedList.add(node4);
        // 输出下链表
        doubleLinkedList.show();
        System.out.println();

        // 删除第1个节点
        doubleLinkedList.delet(1);
        // 输出下链表
        System.out.println("删除第一个1元素后");
        doubleLinkedList.show();
        System.out.println();

        // 再删除下第一个元素
        doubleLinkedList.delet(1);


        // 删除最后一个节点
        doubleLinkedList.delet(4);
        // 输出下链表
        System.out.println("删除最后一个元素后");
        doubleLinkedList.show();


    }
}
