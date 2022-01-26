import com.gradle.robotmotion.Floor;
import com.gradle.robotmotion.Position;
import com.gradle.robotmotion.Robot;
import com.gradle.robotmotion.CommandsDecoder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveTestSalma {
    @Test
    public void testMove(){
        Floor floor = new Floor();
        floor.initializeFloor(20);
        Robot robot=new Robot(floor);
        // (A2,B2,C1,D2)
        robot.setCurrentPosition(new Position(10,10));
        Assertions.assertEquals(10,robot.getCurrentPosition().getxPosition());
        Assertions.assertEquals(10,robot.getCurrentPosition().getyPosition());
        robot.penDown();
        Position position=robot.moveForward(2);
        Assertions.assertEquals(10,position.getxPosition());
        Assertions.assertEquals(12,position.getyPosition());

        // (A2,B2,C1,D2)
        robot=new Robot(floor);
        robot.setCurrentPosition(new Position(floor.getFloorSize()-1,0));
        robot.penDown();
        position=robot.moveForward(10);
        Assertions.assertEquals(floor.getFloorSize()-1,position.getxPosition());
        Assertions.assertEquals(10,position.getyPosition());

        //(A2,B1,C1,D2)
        floor = new Floor();
        floor.initializeFloor(20);
        robot=new Robot(floor);
            //pen is up by default
        robot.setCurrentPosition(new Position(10,10));
        position=robot.moveForward(2);
        Assertions.assertEquals(10,position.getxPosition());
        Assertions.assertEquals(12,position.getyPosition());
        int[][] map = floor.getFloor();

        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(map[i][j], 0);
            }
        }
        //(A2,B2,C2,D2)
        robot=new Robot(floor);
        robot.setCurrentPosition(new Position(10,10));
        robot.turnRight();
        robot.turnRight();
        Assertions.assertEquals(Robot.Orientation.SOUTH,robot.getOrientation());
        position=robot.moveForward(2);
        Assertions.assertEquals(10,position.getxPosition());
        Assertions.assertEquals(8,position.getyPosition());

        //(A2,B2,C3,D2)
        robot=new Robot(floor);
        robot.setCurrentPosition(new Position(10,10));
        robot.turnRight();
        Assertions.assertEquals(Robot.Orientation.EAST,robot.getOrientation());
        position=robot.moveForward(2);
        Assertions.assertEquals(12,position.getxPosition());
        Assertions.assertEquals(10,position.getyPosition());


    }
}
