package components;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Pawel on 16-Dec-16.
 */
public class GravityObject {

    public static final double FORCECONST = 10;

    private boolean pulls;
    private double fieldForce;
    private double fieldRange;
    private double radius;
    private Point point;


    public GravityObject(boolean pulls, double fieldForce, double fieldRange, double radius, Point point) {
        this.pulls = pulls;
        this.fieldForce = fieldForce;
        this.fieldRange = fieldRange;
        this.radius = radius;
        this.point = point;
    }

    public void move() {
    }

    public MyVector2d countForce(Point2D.Double point) {
        MyVector2d vector = new MyVector2d(this.point.getX() - point.getX(), this.point.getY() - point.getY());
        vector.normalize();
        vector.scale(FORCECONST);

        double distance = point.distance(this.point);
        if (fieldRange != 0) {
            vector.scale(100000000 / (distance * distance));
        }
        if(fieldRange != 0 && distance > fieldRange) {
            vector.scale(0);
        }

        if(!pulls) {
            vector.scale(-1);
        }

        return vector;
    }

    public int getX() {
        return (int) point.getX();
    }

    public int getY() {
        return (int) point.getY();
    }

    public double getRadius() {
        return radius;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

}
