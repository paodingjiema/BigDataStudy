package linkedList.singlelinkdeList;

/**
 * @author shkstart
 * @create 2020-04-04 15:13
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        // 先创建一些人物
        HeroNode node1 = new HeroNode(1,"刘亦菲");
        HeroNode node2 = new HeroNode(2,"王祖贤");
        HeroNode node3 = new HeroNode(3,"林青霞");
        HeroNode node4 = new HeroNode(4,"邱淑贞");

        // 创建一个空链表
        LinkedListDefine lld1 = new LinkedListDefine();

        // 向链表中添加元素
        lld1.add(node1);
        lld1.add(node2);
        lld1.add(node3);
        lld1.add(node4);

        // 输出删除之前的链表
        lld1.print();
        System.out.println();
//        lld1.delet(3);
//
//        // 输出删除后的链表
//        lld1.print();
        lld1.reversal();
        // 输出反转后前的链表
        lld1.print();
    }


}
