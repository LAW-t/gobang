package gobang;

import java.util.Scanner;

/**
 * <p> 测试 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName OOP
 * @package gobang
 * @className Test
 * @date 2021/9/29 20:50
 **/
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("你是否要作为房主(Y/N)");
        String s = sc.next();
        if ("N".equals(s)) {
            int port = 6666;
            Receiver receiver = new Receiver(port);
            System.out.println("连接成功");
            receiver.startGame();
        } else {
            System.out.println("对方的ip地址是：");
            String ip = sc.nextLine();
            int port = 6666;
            Sender sender = new Sender(20, 20, 30, 10, ip, port);
            System.out.println("连接成功");
            sender.startGame();
        }
    }
}
