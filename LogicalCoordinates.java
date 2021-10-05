package gobang;

/**
 * <p> 逻辑坐标 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName OOP
 * @package gobang
 * @className Point
 * @date 2021/9/22 10:27
 **/
public class LogicalCoordinates {
    /**
     * 行号
     */
    private int row;
    /**
     * 列号
     */
    private int col;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public LogicalCoordinates() {
    }

    public LogicalCoordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * <p> 将字符串解析成逻辑坐标 </p>
     *
     * @param str
     * @return gobang.LogicalCoordinates
     * @author 汤卫豪
     * @since 2021/9/29
     */
    public static LogicalCoordinates parser(String str) {
        String[] s = str.split(":");
        return new LogicalCoordinates(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
    }

    @Override
    public String toString() {
        return row + ":" + col;
    }
}
