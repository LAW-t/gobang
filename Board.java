package gobang;

/**
 * <p> 棋盘 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName OOP
 * @package gobang
 * @className Board
 * @date 2021/9/22 10:26
 **/
public class Board {
    /**
     * 行数
     */
    private final int rows;
    /**
     * 列数
     */
    private final int cols;
    /**
     * 每一个格子的尺寸
     */
    private final int size;
    /**
     * 棋盘离窗口的边距
     */
    private final int margin;

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
    public Board(int rows, int cols, int size, int margin) {
        this.rows = rows;
        this.cols = cols;
        this.size = size;
        this.margin = margin;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getSize() {
        return size;
    }

    public int getMargin() {
        return margin;
    }

    /**
     * <p> 将物理坐标转化为逻辑坐标 </p>
     *
     * @param x 物理横坐标
     * @param y 物理纵坐标
     * @return gobang.Point 逻辑坐标
     * @author 汤卫豪
     * @since 2021/9/22
     */
    public LogicalCoordinates convert(int x, int y) {
        LogicalCoordinates logicalCoordinates = new LogicalCoordinates();
        logicalCoordinates.setCol((x - margin + size / 2) / size);
        logicalCoordinates.setRow((y - margin + size / 2) / size);
        return logicalCoordinates;
    }

    /**
     * <p> 将逻辑坐标转化为物理坐标 </p>
     *
     * @param logicalCoordinates 逻辑坐标
     * @return gobang.PhysicalCoordinates 物理坐标
     * @author 汤卫豪
     * @since 2021/9/22
     */
    public PhysicalCoordinates convert(LogicalCoordinates logicalCoordinates) {
        PhysicalCoordinates physicalCoordinates = new PhysicalCoordinates();
        physicalCoordinates.setX( size * logicalCoordinates.getCol() + margin);
        physicalCoordinates.setY(size * logicalCoordinates.getRow() + margin);
        return physicalCoordinates;
    }
}
