package gobang;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <p> 游戏 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName OOP
 * @package gobang
 * @className Game
 * @date 2021/9/22 10:27
 **/
public abstract class Game {
    /**
     * 棋盘
     */
    protected Board board = null;
    /**
     * 棋局
     */
    protected Chess chess = null;
    /**
     * 画笔
     */
    protected Graphics pen;
    /**
     * 画布
     */
    protected Canvas canvas;
    /**
     * 信号值
     */
    protected boolean flag = true;

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
    protected Game(int rows, int cols, int size, int margin) {
        board = new Board(rows, cols, size, margin);
        chess = new Chess(rows, cols);
    }
    protected Game(){}
    /**
     * <p> 画棋盘 </p>
     *
     * @author 汤卫豪
     * @since 2021/9/22
     */
    public void drawBoard() {
        // 得到画笔
        pen = canvas.getGraphics();
        // 设置颜色
        pen.setColor(Color.BLACK);
        int x = board.getMargin();
        int startY = board.getMargin();
        int endY = startY + board.getSize() * (board.getCols() - 1);
        // 绘制经纬
        for (int i = 0; i < board.getCols(); i++) {
            pen.drawLine(x, startY, x, endY);
            x += board.getSize();
        }
        // 绘制纬线
        int y = board.getMargin();
        int startX = board.getMargin();
        int endX = startX + board.getSize() * (board.getRows() - 1);
        // 绘制经纬
        for (int i = 0; i < board.getRows(); i++) {
            pen.drawLine(startX, y, endX, y);
            y += board.getSize();
        }
    }

    /**
     * <p> 画棋子 </p>
     *
     * @param point 要画的棋子
     * @param color 棋子颜色
     * @author 汤卫豪
     * @since 2021/9/22
     */
    public void drawChess(LogicalCoordinates point, byte color) {
        // 设置画笔颜色
        pen.setColor(color == Chess.BLACK ? Color.BLACK : Color.WHITE);
        // 棋子大小
        int radius = board.getSize() / 3;
        PhysicalCoordinates physicalCoordinates = board.convert(point);
        physicalCoordinates.setX(physicalCoordinates.getX() - radius);
        physicalCoordinates.setY(physicalCoordinates.getY() - radius);
        pen.fillArc(physicalCoordinates.getX(), physicalCoordinates.getY(), 2 * radius, 2 * radius,
                0, 360);
    }

    /**
     * <p> 落子的处理逻辑 </p>
     *
     * @param physicalCoordinates 物理坐标
     * @return byte 赢家，Chess.NONE没有赢方
     * @author 汤卫豪
     * @since 2021/9/22
     */
    public byte handle(LogicalCoordinates logicalCoordinates) {
        if (chess.exist(logicalCoordinates)) {
            return Chess.NONE;
        }
        // 落子
        chess.add(logicalCoordinates);
        // 画棋子
        drawChess(logicalCoordinates, chess.getTurn());
        // 返回输赢结果
        byte result = chess.win(logicalCoordinates);
        if (result == Chess.NONE) {
            chess.change();
            return Chess.NONE;
        } else {
            return result;
        }
    }

    /**
     * <p> 包含网络传输的落子逻辑 </p>
     *
     * @param logicalCoordinates 逻辑坐标
     * @param net 网络
     * @return byte
     * @author 汤卫豪
     * @since 2021/9/30
     */
    public byte handleNetwork(LogicalCoordinates logicalCoordinates, Net net) {
        if (!chess.exist(logicalCoordinates)) {
            net.send(logicalCoordinates.toString());
        }
        return handle(logicalCoordinates);
    }
    /**
     * <p> 初始化 </p>
     *
     * @param
     * @author 汤卫豪
     * @since 2021/9/29
     */
    public void init() {
        // 窗体
        Frame frame;
        // 初始化窗体
        int width = board.getSize() * (board.getCols() - 1) + 2 * board.getMargin();
        int height = board.getSize() * (board.getRows() - 1) + 2 * board.getMargin();
        frame = new Frame("我的五子棋");
        frame.setSize(width + 100, height + 100);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setBackground(Color.gray);

        // 准备画布
        canvas = new Canvas();
        canvas.setSize(width, height);
        frame.add(canvas);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        drawBoard();
    }

    /**
     * <p> 开始游戏 </p>
     *
     * @author 汤卫豪
     * @since 2021/9/22
     */
    public abstract void startGame();


    /**
     * <p> 胜利状态 </p>
     *
     * @param res 结果
     * @return boolean true游戏结束
     * @author 汤卫豪
     * @since 2021/9/29
     */
    public boolean winStatue(byte res) {
        if (res != Chess.NONE) {
            String actor = res == Chess.BLACK ? "黑方" : "白方";
            String msg = String.format("恭喜%s获胜！", actor);
            JOptionPane.showMessageDialog(null, msg, "游戏结束",
                    JOptionPane.WARNING_MESSAGE, null);
            //结束
            return true;
        }
        return false;
    }
}
