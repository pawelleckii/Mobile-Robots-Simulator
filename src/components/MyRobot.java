package components;

import main.Main;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawel on 16-Dec-16.
 */
public class MyRobot {

    private Point2D.Double point;
    private int height;
    private int width;
    private double speed; // px/sec
    public List<Point2D.Double> trajectory;
    private Color color;

    public MyRobot(Point2D.Double point, int height, int width, double speed) {
        this.point = point;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.color = randomizeColor();
        trajectory = new ArrayList<>();
    }

    public int getY() {
        return (int) point.getY();
    }

    public int getX() {
        return (int) point.getX();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Point2D.Double getPoint() {
        return point;
    }

    public double getSpeed() {
        return speed;
    }

    public void move(List<GravityObject> gravityObjects) {
        MyVector2d moveVector = new MyVector2d(0, 0);
        for(GravityObject gravityObject : gravityObjects) {
            moveVector.add(gravityObject.countForce(point));
        }
        moveVector.normalize();
        moveVector.scale(speed * Main.TIME / 1000);

        point = new Point2D.Double(point.x + moveVector.x, point.y + moveVector.y);
        //System.out.print("\r" + moveVector.x + " " + moveVector.y);
    }

    private static Color randomizeColor(){
        int R = (int) (Math.random( )*256);
        int G = (int)(Math.random( )*256);
        int B = (int)(Math.random( )*256);
        Color randomColor = new Color(R, G, B);
        return randomColor;
    }

    public void addTrajectory(){
        Point2D.Double newPoint = this.point;
        this.trajectory.add(newPoint);
    }

    public Color getColor() {
        return color;
    }
}
