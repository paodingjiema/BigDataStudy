package linkedList.singlelinkdeList;

/**
 * @author shkstart
 * @create 2020-04-04 14:14
 * 模拟带头节点链表
 */
public class LinkedListDefine {
    // 定义头节点
    HeroNode head = new HeroNode(0," ");

    // 在链表最后加入节点, 参数为加入的节点
    public void add(HeroNode heroNode){
        // 设置临时变量
        HeroNode temp = head;

        // 找到最后一个节点
        while (temp.next != null){
            temp = temp.next;
        }
        // 将要加入的节点加入最后
        temp.next = heroNode;
    }

    // 删除指定编号的元素
        // 找到要删除节点的前一个位置
    public void  delet(int i){
        // 设置临时变量,temp
        HeroNode temp = head;

        // 设置标志位，判断是否找到
        Boolean flag = false;

        while (temp.next != null){
            if(temp.next.id == i){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        // 如果flag为true就找到了该节点，temp为前一个位置
        if(flag == true){
            temp.next = temp.next.next;
        } else {
            System.out.println("该链表中没有这个位置的节点");
        }
    }

    // 打印该链表
    public void print(){
        // 设置临时变量
        HeroNode temp = head;
        if (temp.next == null){
            System.out.println("链表为空");
        } else {
            while (temp.next != null){
                temp = temp.next;
                System.out.println(temp);
            }
        }
    }

    // 实现链表对反转
    public void reversal(){
        // 定义一个临时变量指向链表的当前位置,从第一个位置开始
        HeroNode curent = head.next;
        // 定义一个变量为current的下一个位置
        HeroNode curent_next ;
        // 定义一个新的头节点
        HeroNode revers = new HeroNode(0," ");

        while (curent != null){
            curent_next = curent.next;

            curent.next = revers.next;
            revers.next = curent;

            curent = curent_next;
        }

        // 将新的头节点赋值给原来的头节点
        head = revers;
    }
}





































