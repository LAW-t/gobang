package gobang;

/**
 * <p> 物理坐标 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName OOP
 * @package gobang
 * @className PhysicalCoordinates
 * @date 2021/9/22 18:35
 **/
public class PhysicalCoordinates {
    /**
     * 屏幕横坐标
     */
    private int x;
    /**
     * 屏幕纵坐标
     */
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PhysicalCoordinates() {
    }

    public PhysicalCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
