package components;

/**
 * Created by Pawel on 10-Jan-17.
 */
public class MyVector2d {

    public double x;
    public double y;

    public MyVector2d() {
    }

    public MyVector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public final void normalize() {
        double var1 = 1.0D / Math.sqrt(this.x * this.x + this.y * this.y);
        this.x *= var1;
        this.y *= var1;
    }

    public final void scale(double var1) {
        this.x *= var1;
        this.y *= var1;
    }
    public final void add(MyVector2d var1) {
        this.x += var1.x;
           this.y += var1.y;
    }
}