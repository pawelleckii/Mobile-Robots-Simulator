package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.List;


/**
 * Created by Pawel on 16-Dec-16.
 */
public class MyPanel extends JPanel {

    private JFrame frame = new JFrame();
    private World world;

    private final Point worldBasePoint = new Point(50, 50);

    public static final int height = 800;
    public static final int width = 600;


    public MyPanel() {
        initPanel();
    }

    private void initPanel() {
        frame.setTitle("MrRoboto");
        frame.setContentPane(this);
        frame.setBounds(600, 100, width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                MyRobot robot = new MyRobot(new Point2D.Double(e.getX() - worldBasePoint.getX(), e.getY() - worldBasePoint.getY() - 20), 10, 10, 100);
                world.addRobot(robot);
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int worldX = (int) worldBasePoint.getX();
        int worldY = (int) worldBasePoint.getY();



        synchronized (world.myRobots) {
            if (world.getMyRobots().size() != 0) {
                List<MyRobot> myRobots = world.getMyRobots();
                for (MyRobot myRobot : myRobots) {
                    g.setColor(myRobot.getColor());
                    g.fillRect(worldX + myRobot.getX(), worldY + myRobot.getY(), myRobot.getWidth(), myRobot.getHeight());
                    java.util.List<Point2D.Double> trajectory = myRobot.trajectory;
                    for (int i = 1; i < trajectory.size(); i++) {
                        Point2D.Double lastPoint = trajectory.get(i - 1);
                        Point2D.Double point = trajectory.get(i);
                        g.drawLine(worldX + (int) lastPoint.x + myRobot.getWidth()/2, worldY + (int) lastPoint.y+ myRobot.getHeight()/2, worldX + (int) point.x+ myRobot.getWidth()/2, worldY + (int) point.y+ myRobot.getHeight()/2);
                    }

                }
            }
        }
        g.setColor(Color.black);
        if (world.getGravityObjects().size() != 0) {
            for (GravityObject gravityObject : world.getGravityObjects()) {
                int x = gravityObject.getX();
                int y = gravityObject.getY();
                int radius = (int) gravityObject.getRadius();
                g.fillOval(worldX + x - radius, worldY + y - radius, radius * 2, radius * 2);
            }
        }
        g.drawRect(worldX, worldY, world.getWidth(), world.getHeight());

        repaint();
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void paintWorld() {
        paintComponent(this.getGraphics());
    }
}
