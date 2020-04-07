package linkedList.singlelinkdeList.doubleLinkedList;

/**
 * @author shkstart
 * @create 2020-04-06 10:13
 * 该类为双向链表类
 */
public class DoubleLinkedList {
    public Node head = new Node(0,""); // 定义头节点

    // 添加元素的方法
    public void add(Node node){
        // 定义一个辅助指针
        Node temp = head;

        // 找到最后的节点
        while (temp.next != null){
            temp = temp.next;
        }

        // 将节点加入
        temp.next = node;
        node.pre = temp;
    }

    // 删除指定编号的元素
    public void delet(int i){
        if(head .next == null){
            System.out.println("链表为空，无法删除");
            return;
        }

        // 定义一个辅助指针,curent表示第一个元素
        Node curent = head.next;

        // 定义一个标志位，判断是否找到
        Boolean flag = false;

        while (curent != null){
            if(curent.id == i){     // 找到了就退出，并将flag置为true
                flag = true;
                break;
            }
            curent = curent.next;
        }

        // 进行删除节点的操作
        if(flag == true){   // 找到了该节点,curent即为该节点
            curent.pre.next = curent.next;
            if(curent.next != null){    // 该节点不为最后一个节点
                curent.next.pre = curent.pre;
            } else {
                curent.pre = null;
            }
        } else {
            System.out.println("没找到该节点，无法删除");
        }
    }

    // 打印该双向链表
    public void show() {
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 定义一个辅助指针
        Node curent = head.next;
        while(curent != null){
            System.out.println(curent);
            curent = curent.next;
        }
    }
}
