import com.gradle.robotmotion.Floor;
import com.gradle.robotmotion.Position;
import com.gradle.robotmotion.Robot;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.powermock.api.mockito.PowerMockito.whenNew;

public class RobotTests {

    static Floor floor;
    static Robot robot;

    //TODO BeforeEach and AfterEach

    @Test
    public void testGetOrientation(){
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);

        Assertions.assertEquals(Robot.Orientation.NORTH, robot.getOrientation());
    }

    @Test
    public void testGetPenUp(){
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);

        Assertions.assertTrue(robot.getPenUp());
    }


    @Test
    public void testGetCurrentPosition(){
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);

        Position defaultPosition = new Position(0,0);
        Assertions.assertEquals(defaultPosition.toString(), robot.getCurrentPosition().toString());
    }

    @Test
    public void testTurnRight(){
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);

        robot.turnRight();
        Assertions.assertEquals(Robot.Orientation.EAST,robot.getOrientation());
        robot.turnRight();
        Assertions.assertEquals(Robot.Orientation.SOUTH,robot.getOrientation());
        robot.turnRight();
        Assertions.assertEquals(Robot.Orientation.WEST,robot.getOrientation());
        robot.turnRight();
        Assertions.assertEquals(Robot.Orientation.NORTH,robot.getOrientation());
    }

    @Test
    public void testTurnLeft(){
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);

        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.WEST,robot.getOrientation());
        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.SOUTH,robot.getOrientation());
        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.EAST,robot.getOrientation());
        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.NORTH,robot.getOrientation());
    }

    @Test
    public void testPenUp() {
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);

        robot.penUp();
        Assertions.assertTrue(robot.getPenUp());

    }

    @Test
    public void testPenDown() {
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);

        robot.penDown();
        Assertions.assertFalse(robot.getPenUp());

    }

    @Test
    public void testPrintCurrentPosition() {
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);

        robot.penUp();
        Assertions.assertEquals("Position: {0,0} - Pen: Up - Facing: NORTH","Position: " + robot.getCurrentPosition().toString() + " - Pen: Up - Facing: " + robot.getOrientation().toString());

        robot.penDown();
        Assertions.assertEquals("Position: {0,0} - Pen: Down - Facing: NORTH","Position: " + robot.getCurrentPosition().toString() + " - Pen: Down - Facing: " + robot.getOrientation().toString());


    }


}
