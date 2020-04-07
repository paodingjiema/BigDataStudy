package circleQueue;

/**
 * @author shkstart
 * @create 2020-04-02 14:31
 * 该类是定义一个环形队列
 */
public class queueDefine {
    public int front; // 表示队列第一个元素的前一个位置,默认为0
    public int real;  // 表示队列最后一个元素的位置，默认为0
    public int size; // 表示队列中元素的个数
    public int[] a;  // 用数组模拟环形队列

    // 用带参构造初始化队列,传进的是队列的长度
    public queueDefine(int size) {
        this.size = size;
        // 造一个长度为队列长度加1的数组模拟环形队列,这样方便
        a = new int[size+1];
    }
}
