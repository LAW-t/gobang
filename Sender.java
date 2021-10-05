package gobang;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <p> 发送端 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName OOP
 * @package gobang
 * @className Sender
 * @date 2021/9/29 20:30
 **/
public class Sender extends Game{
    private Net net = new Net();
    private String ip;
    private int port;
    private boolean flag = true;
    /**
     * <p> 构造方法 </p>
     *
     * @param rows   行数
     * @param cols   列数
     * @param size   大小
     * @param margin 边距
     * @author 汤卫豪
     * @since 2021/9/22
     */
    public Sender(int rows, int cols, int size, int margin, String ip, int port) {
        super(rows, cols, size, margin);
        this.ip = ip;
        this.port = port;
        net.bind(ip, port);
        net.send(String.format("%d:%d:%d:%d", rows, cols, size, margin));
    }



    @Override
    public void startGame() {
        init();
        while (true) {
            canvas.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    LogicalCoordinates logicalCoordinates = board.convert(e.getX(), e.getY());
                    if (flag) {
                        flag = false;
                        byte res = Sender.super.handleNetwork(logicalCoordinates, net);
                        if (Sender.super.winStatue(res)) {
                            System.exit(0);
                        }
                    }
                }
            });
            byte res = Sender.super.handle(LogicalCoordinates.parser(net.receive()));
            flag = true;
            if (Sender.super.winStatue(res)) {
                break;
            }
        }
        System.exit(0);
    }
}
