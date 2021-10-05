package gobang;

/**
 * 棋局
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName OOP
 * @package gobang
 * @className Chess
 * @date 2021/9/22 10:27
 */
public class Chess {
    /**
     * 无子
     */
    public static final byte NONE = 0;
    /**
     * 白子
     */
    public static final byte WHITE = -1;
    /**
     * 黑子
     */
    public static final byte BLACK = 1;
    /**
     * 状态
     */
    private final byte[][] status;
    /**
     * 落子方
     */
    private byte turn;


    public byte getTurn() {
        return turn;
    }

    /**
     * 构造函数
     *
     * @param rows 行数
     * @param cols 列数
     * @author 汤卫豪
     * @since 2021/9/22
     */
    public Chess(int rows, int cols) {
        status = new byte[rows][cols];
        turn = BLACK;
    }

    /**
     * 判断某个位置是否有棋子
     *
     * @param point 落点
     * @return boolean true有棋子，false无棋子
     * @author 汤卫豪
     * @since 2021/9/22
     */
    public boolean exist(LogicalCoordinates point) {
        return status[point.getRow()][point.getCol()] != NONE;
    }

    /**
     * 落点
     *
     * @param point 落点的坐标
     * @author 汤卫豪
     * @since 2021/9/22
     */
    public void add(LogicalCoordinates point) {
        status[point.getRow()][point.getCol()] = turn;
    }


    /**
     * 判断输赢
     *
     * @param point 待判断的点
     * @return byte EMPTY-无，WHITE-白方胜，BLACK-黑方胜
     * @author 汤卫豪
     * @since 2021/9/22
     */
    public byte win(LogicalCoordinates point) {
        int[] ddx = {-1, -1, -1, 0};
        int[] ddy = {-1, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            int otherX = point.getRow() + ddx[i];
            int otherY = point.getCol() + ddy[i];
            int cnt = 0;
            int turnCnt = 1;
            while (otherX >= 0 && otherX < status.length && otherY >= 0 && otherY < status[0].length && cnt < 5
                    && status[otherX][otherY] == turn) {
                if (status[otherX][otherY] == turn) {
                    turnCnt++;
                }
                cnt++;
                otherX += ddx[i];
                otherY += ddy[i];
            }
            otherX = point.getRow() - ddx[i];
            otherY = point.getCol() - ddy[i];
            while (otherX >= 0 && otherX < status.length && otherY >= 0 && otherY < status[0].length && cnt < 10
                    && status[otherX][otherY] == turn) {
                if (status[otherX][otherY] == turn) {
                    turnCnt++;
                }
                cnt++;
                otherX -= ddx[i];
                otherY -= ddy[i];
            }
            if (turnCnt >= 5) {
                return turn;
            }
        }
        return NONE;
    }

    /**
     * 交换回合
     *
     * @author 汤卫豪
     * @since 2021/9/22
     */
    public void change() {
        if (turn == WHITE) {
            turn = BLACK;
        } else {
            turn = WHITE;
        }
    }
}
