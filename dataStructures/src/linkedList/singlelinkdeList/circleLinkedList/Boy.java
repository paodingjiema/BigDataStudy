package linkedList.singlelinkdeList.circleLinkedList;

/**
 * @author shkstart
 * @create 2020-04-06 11:09
 * 这里和josePhu问题结合起来。用单向环形链表解决约瑟夫问题
 * 定义一个孩子类
 */
public class Boy {
    public int id;
    Boy next; // 定义一个指针，指向下一个元素

    public Boy(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Boy{" + "id=" + id + '}';
    }
}
