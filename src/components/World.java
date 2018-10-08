package components;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pawel on 16-Dec-16.
 */
public class World {
    private List<GravityObject> gravityObjects = new ArrayList<>();
    public List<MyRobot> myRobots = Collections.synchronizedList(new ArrayList<>());
    private final int height = 700;
    private final int width = 500;

    public World() {
    }

    public void moveObjects(int time) {
        synchronized (myRobots){
            for(MyRobot myRobot : myRobots) {
                myRobot.move(gravityObjects);
            }
        }

        for(GravityObject gravityObject : gravityObjects) {
            gravityObject.move();
        }

    }
    public void createTrajectories(int time){
        synchronized (myRobots){
            for(MyRobot myRobot : myRobots) {
                myRobot.addTrajectory();
            }
        }
    }

    public void addGravityObject(GravityObject gravityObject) {
        gravityObjects.add(gravityObject);
    }

    public void addRobot(MyRobot myRobot) {
        myRobots.add(myRobot);
    }

    public void removeGravityObjects(GravityObject aim){
        synchronized (myRobots) {
            Iterator<MyRobot> iterator = myRobots.iterator();
            while (iterator.hasNext()) {
                MyRobot myRobot = iterator.next();
                if(myRobot.getPoint().distance(aim.getPoint()) < 1){
                    iterator.remove();
                }
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<GravityObject> getGravityObjects() {
        return gravityObjects;
    }

    public List<MyRobot> getMyRobots() {
        return myRobots;
    }
}
