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

    @BeforeAll
    public static void setUp(){
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);
    }

    @Test
    public void testGetOrientation(){
        Assertions.assertEquals(Robot.Orientation.NORTH, robot.getOrientation());
    }

    @Test
    public void testGetPenUp(){
        Assertions.assertEquals(true, robot.getPenUp());
    }

    @Test
    public void testGetCurrentPosition(){
        Position defaultPosition = new Position(0,0);
        Assertions.assertEquals(defaultPosition.toString(), robot.getCurrentPosition().toString());
    }

    @Test
    public void testTurnRight(){
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
        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.WEST,robot.getOrientation());
        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.SOUTH,robot.getOrientation());
        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.EAST,robot.getOrientation());
        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.NORTH,robot.getOrientation());
    }

    @AfterAll
    public static void teardown(){
        floor = null;
        robot=null;
    }

}
