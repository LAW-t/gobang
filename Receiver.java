package gobang;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <p> 接收端 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName OOP
 * @package gobang
 * @className Receiver
 * @date 2021/9/29 20:30
 * @TODO
 **/
public class Receiver extends Game{
    private Net net = new Net();
    private int port;

    /**
     * <p> 构造方法 </p>
     *
     * @author 汤卫豪
     * @since 2021/9/22
     */
    public Receiver(int port) {
        this.port = port;
        net.ready(port);
        System.out.println("连接成功");
        String[] config = net.receive().split(":");
        this.board = new Board(Integer.parseInt(config[0]), Integer.parseInt(config[1]),
                Integer.parseInt(config[2]), Integer.parseInt(config[3]));
        this.chess = new Chess(Integer.parseInt(config[0]), Integer.parseInt(config[1]));
    }

    @Override
    public void startGame() {
        init();
        while (true) {
            byte res = Receiver.super.handle(LogicalCoordinates.parser(net.receive()));
            flag = true;
            if (Receiver.super.winStatue(res)) {
                break;
            }
            canvas.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    LogicalCoordinates logicalCoordinates = board.convert(e.getX(), e.getY());
                    if (flag) {
                        flag = false;
                        byte res = Receiver.super.handleNetwork(logicalCoordinates, net);
                        if (Receiver.super.winStatue(res)) {
                            System.exit(0);
                        }
                    }
                }
            });
        }

        System.exit(0);
    }
}
