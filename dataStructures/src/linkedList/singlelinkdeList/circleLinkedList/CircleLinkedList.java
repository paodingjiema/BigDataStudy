package linkedList.singlelinkdeList.circleLinkedList;

/**
 * @author shkstart
 * @create 2020-04-06 11:12
 */
public class CircleLinkedList {
    Boy first;  // 定义fir指针，指向第一个元素

    // 添加元素
    public void add(int num){   // num表示链表中孩子的个数
        Boy curent = new Boy(0); // 定义一个辅助指针
        for(int i = 1; i <= num; i++){
            Boy newboy = new Boy(i);
            if (i == 1) {        // 第一个元素有些特殊
                first = newboy;
                first.next = newboy;
                curent = newboy;
            } else {
                curent.next = newboy;
                newboy.next = first;
                curent = newboy;
            }
        }
    }

    // 遍历链表
    public void show(){
         if(first == null){
             System.out.println("链表为空");
             return;
         }

         if(first.next == first){   // 只有一个元素
             System.out.println(first);
             return;
         }

         Boy curent = first;// 创建一个辅助指针
       while (curent.next != first){    //  最后一个元素没有输出
           System.out.println(curent);
           curent = curent.next;
       }
        System.out.println(curent);
    }
}
