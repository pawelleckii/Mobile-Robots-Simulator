package main;

import components.GravityObject;
import components.MyPanel;
import components.MyRobot;
import components.World;


import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Pawel on 16-Dec-16.
 */
public class Main {

    World world = new World();
    static GravityObject target;
    public static final int TIME = 10;
    public static double timeFromStart = Math.random( ) * 5000 ;
    private static final double PENDULUMTIME = 4;

    public static void main(String[] args) throws InterruptedException {

        World world = createCustomWorld();
        MyPanel myPanel = new MyPanel();
        myPanel.setWorld(world);

        while(true) {
            world.moveObjects(TIME);
            world.removeGravityObjects(target);
            world.createTrajectories(TIME);
            myPanel.paintWorld();
            Thread.sleep(TIME);
            timeFromStart += TIME;
        }

    }

    private static World createCustomWorld() {
        World world = new World();

        Point pendulumPoint = new Point(world.getWidth()/2, world.getHeight()/2);
        GravityObject pendulum = new GravityObject(false, 5, 150, 25, pendulumPoint) {
            @Override
            public void move() {
                super.move();

                Point oldPoint = this.getPoint();
                int x = world.getWidth()/2 + (int) (world.getWidth()/2 * Math.sin(timeFromStart / 1000 * Math.PI / PENDULUMTIME ) * 0.9);
                this.setPoint(new Point(x, (int) oldPoint.getY()));
            }
        };
        world.addGravityObject(pendulum);

        Point targetPoint = new Point(world.getWidth()/2, 30);
        target = new GravityObject(true, 50, 0, 5, targetPoint);
        world.addGravityObject(target);

        Point2D.Double robotPoint = new Point2D.Double(world.getWidth()/2, world.getHeight() - 30);
        MyRobot robot = new MyRobot(robotPoint, 10, 10, 100);
        world.addRobot(robot);

        return world;
    }


}
