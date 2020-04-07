package circleQueue;

/**
 * @author shkstart
 * @create 2020-04-02 14:42
 * 环形队列的工具类，增删改查，判断是否为空
 */
public class queueTools {

    // 判断队列是否为空,ture为空，false不空
    public Boolean isEmpty(queueDefine queue) {
        return queue.front == queue.real;
    }

    // 判断对列是否满了。true就是满了
    public Boolean isFull(queueDefine queue) {
        // queue.real = (queue.real + 1) % (queue.size + 1);
        // 这里有个bug，假如queue的real = 0的话，插入一个数字是在1的位置，
        // 经过上面的操作就会real 变为1;实际上是在2插入数字的


        //        int temp = queue.real;
        //        temp = (temp + 1) % (queue.size + 1);
        //        return temp == queue.front;
        // 这里不加临时变量也可以
        return (queue.real + 1) % (queue.size + 1) == queue.front;
    }

    // 添加元素
    public void add(queueDefine queue, int num) {
        // 队列没满的话
        if (!isFull(queue)) {
            queue.real = (queue.real + 1) % (queue.size + 1);
            queue.a[queue.real] = num;
        } else {
            System.out.println("队列已经满了，插入失败");
        }
    }

    // 删除元素,并返回元素
    public void delet(queueDefine queue) {
        // 队列中有元素
        if (!isEmpty(queue)) {
            queue.front = (queue.front + 1) % (queue.size + 1);
            System.out.println("成功删除" + queue.a[queue.front]);
        } else {
            System.out.println("队列为空，无法删除");
        }
    }

    // 打印所有元素
    public void print(queueDefine queue) {

    //        while (queue.front != queue.real) {
    //            queue.front = (queue.front + 1) % (queue.size + 1);
    //            System.out.println(queue.a[queue.front]);
    //        }
        // 这里有个bug，因为是直接传参的要注意变量。这里最后，队列变为空了
        int temp1 = queue.front;
        int temp2 = queue.real;
        while(temp1 != temp2){
            temp1 = (temp1 + 1) % (queue.size + 1);
            System.out.print(queue.a[temp1] + "  ");
        }
        System.out.println();
    }
}
















