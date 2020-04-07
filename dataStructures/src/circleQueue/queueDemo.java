package circleQueue;

import java.util.Scanner;

/**
 * @author shkstart
 * @create 2020-04-02 15:12
 */
public class queueDemo {
    public static void main(String[] args) {
        // 创建长度为5的队列
        queueDefine queue1 = new queueDefine(5);

        // 创建队列的工具类
        queueTools qt1 = new queueTools();

        // 从键盘录入
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        System.out.println("******a.添加元素");
        System.out.println("******d.删除元素");
        System.out.println("******p.打印队列中所有元素");
        System.out.println("******e.退出循环");
        System.out.print(" ");
        while (true) {
            System.out.print("  ");
            System.out.println("请输入你的选择：");
            String num = sc.nextLine();
            if (num.equals("e")) {
                break;
            } else {
                switch (num) {
                    case "a":
                        System.out.println("请输入你要加入的数");
                        int num2 = sc2.nextInt();
                        qt1.add(queue1, num2);
                        break;
                    case "d":
                        qt1.delet(queue1);
                        break;
                    case "p":
                        qt1.print(queue1);
                        break;

                }
            }
        }
    }
}




























