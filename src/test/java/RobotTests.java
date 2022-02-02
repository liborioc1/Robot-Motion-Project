import com.gradle.robotmotion.Floor;
import com.gradle.robotmotion.Position;
import com.gradle.robotmotion.Robot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RobotTests {

    static Floor floor;
    static Robot robot;



    @Test
    public void testGetOrientation(){
        //t21
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);
        Assertions.assertEquals(Robot.Orientation.NORTH, robot.getOrientation());
    }

    @Test
    public void testGetPenUp(){
        //t22
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);
        Assertions.assertTrue(robot.getPenUp());
    }


    @Test
    public void testGetCurrentPosition(){
        //t23
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);

        Position defaultPosition = new Position(0,0);
        Assertions.assertEquals(defaultPosition.toString(), robot.getCurrentPosition().toString());

        //t24
        Position position = new Position(9,9);
        robot.setCurrentPosition(new Position(9,9));
        Assertions.assertEquals(position.toString(), robot.getCurrentPosition().toString());
    }

    @Test
    public void testTurnRight(){
        //t25
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);

        robot.turnRight();
        Assertions.assertEquals(Robot.Orientation.EAST,robot.getOrientation());
        //t26
        robot.turnRight();
        Assertions.assertEquals(Robot.Orientation.SOUTH,robot.getOrientation());
        //t27
        robot.turnRight();
        Assertions.assertEquals(Robot.Orientation.WEST,robot.getOrientation());
        //t28
        robot.turnRight();
        Assertions.assertEquals(Robot.Orientation.NORTH,robot.getOrientation());

    }

    @Test
    public void testTurnLeft(){
        //t29
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);

        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.WEST,robot.getOrientation());
        //t30
        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.SOUTH,robot.getOrientation());
        //t31
        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.EAST,robot.getOrientation());
        //t32
        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.NORTH,robot.getOrientation());
    }

    @Test
    public void testPenUp() {
        //t33
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);

        robot.penUp();
        Assertions.assertTrue(robot.getPenUp());
        //t34
        robot.penDown();
        robot.penUp();
        Assertions.assertTrue(robot.getPenUp());

    }

    @Test
    public void testPenDown() {
        //t35
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);

        robot.penDown();
        Assertions.assertFalse(robot.getPenUp());

        //t36
        robot.penDown();
        robot.penDown();
        Assertions.assertFalse(robot.getPenUp());

    }

    @Test
    public void testPrintCurrentPosition() {
        //t37
        floor = new Floor();
        robot=new Robot(floor);
        floor.initializeFloor(10);

        robot.penUp();
        Assertions.assertEquals("Position: {0,0} - Pen: Up - Facing: NORTH",robot.printCurrentPosition());

        //t38
        robot.penDown();
        Assertions.assertEquals("Position: {0,0} - Pen: Down - Facing: NORTH",robot.printCurrentPosition());


    }

    @Test
    public void testMoveForward(){

        Floor floor = new Floor();
        floor.initializeFloor(20);
        Robot robot=new Robot(floor);

        //t39 -> (A2,B2,C1,D2)

        robot.setCurrentPosition(new Position(10,10));
        Assertions.assertEquals(10,robot.getCurrentPosition().getxPosition());
        Assertions.assertEquals(10,robot.getCurrentPosition().getyPosition());
        robot.penDown();
        Position position=robot.moveForward(2);
        Assertions.assertEquals(10,position.getxPosition());
        Assertions.assertEquals(12,position.getyPosition());
        Floor testFloor=new Floor();
        testFloor.initializeFloor(20);
        for(int i = 10; i <=12; i++) {
            testFloor.getFloor()[10][i]=1;
        }
        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(floor.getFloor()[i][j],  testFloor.getFloor()[i][j]);
            }
        }

        //t40 -> (A3,B2,C1,D2)

        floor = new Floor();
        floor.initializeFloor(20);
        robot=new Robot(floor);
        robot.setCurrentPosition(new Position(floor.getFloorSize()-1,0));
        robot.penDown();
        position=robot.moveForward(10);
        Assertions.assertEquals(floor.getFloorSize()-1,position.getxPosition());
        Assertions.assertEquals(10,position.getyPosition());
        testFloor=new Floor();
        testFloor.initializeFloor(20);
        for(int i = 0; i <=10; i++) {
            testFloor.getFloor()[testFloor.getFloorSize()-1][i]=1;
        }
        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(floor.getFloor()[i][j],  testFloor.getFloor()[i][j]);
            }
        }

        //t41 -> (A2,B1,C1,D2)

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

        //t42 -> (A2,B2,C2,D2)

        robot=new Robot(floor);
        robot.setCurrentPosition(new Position(10,10));
        robot.penDown();
        robot.turnRight();
        robot.turnRight();
        Assertions.assertEquals(Robot.Orientation.SOUTH,robot.getOrientation());
        position=robot.moveForward(2);
        Assertions.assertEquals(10,position.getxPosition());
        Assertions.assertEquals(8,position.getyPosition());
        testFloor=new Floor();
        testFloor.initializeFloor(20);
        for(int i = 8; i <=10; i++) {
            testFloor.getFloor()[10][i]=1;
        }
        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(floor.getFloor()[i][j],  testFloor.getFloor()[i][j]);
            }
        }

        //t43 -> (A2,B2,C3,D2)

        floor = new Floor();
        floor.initializeFloor(20);
        robot=new Robot(floor);
        robot.setCurrentPosition(new Position(10,10));
        robot.penDown();
        robot.turnRight();
        Assertions.assertEquals(Robot.Orientation.EAST,robot.getOrientation());
        position=robot.moveForward(2);
        Assertions.assertEquals(12,position.getxPosition());
        Assertions.assertEquals(10,position.getyPosition());
        testFloor=new Floor();
        testFloor.initializeFloor(20);
        for(int i = 10; i <=12; i++) {
            testFloor.getFloor()[i][10]=1;
        }
        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(floor.getFloor()[i][j],  testFloor.getFloor()[i][j]);
            }
        }

        //t44 -> (A2,B2,C4,D2)

        floor = new Floor();
        floor.initializeFloor(20);
        robot=new Robot(floor);
        robot.setCurrentPosition(new Position(10,10));
        robot.penDown();
        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.WEST,robot.getOrientation());
        position=robot.moveForward(2);
        Assertions.assertEquals(8,position.getxPosition());
        Assertions.assertEquals(10,position.getyPosition());
        testFloor=new Floor();
        testFloor.initializeFloor(20);
        for(int i = 8; i <=10; i++) {
            testFloor.getFloor()[i][10]=1;
        }
        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(floor.getFloor()[i][j],  testFloor.getFloor()[i][j]);
            }
        }

        //t45 -> (A2,B2,C1,D1)

        floor = new Floor();
        floor.initializeFloor(20);
        robot=new Robot(floor);
        robot.setCurrentPosition(new Position(10,10));
        Assertions.assertEquals(10,robot.getCurrentPosition().getxPosition());
        Assertions.assertEquals(10,robot.getCurrentPosition().getyPosition());
        robot.penDown();
        position=robot.moveForward(0);
        Assertions.assertEquals(10,position.getxPosition());
        Assertions.assertEquals(10,position.getyPosition());
        testFloor=new Floor();
        testFloor.initializeFloor(20);
        for(int i = 10; i <=10; i++) {
            testFloor.getFloor()[10][i]=1;
        }
        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(floor.getFloor()[i][j],  testFloor.getFloor()[i][j]);
            }
        }

        //t46 -> (A2,B2,C1,D3)

        floor = new Floor();
        floor.initializeFloor(20);
        robot=new Robot(floor);
        robot.setCurrentPosition(new Position(10,10));
        Assertions.assertEquals(10,robot.getCurrentPosition().getxPosition());
        Assertions.assertEquals(10,robot.getCurrentPosition().getyPosition());
        robot.penDown();
        position=robot.moveForward(9);
        Assertions.assertEquals(10,position.getxPosition());
        Assertions.assertEquals(19,position.getyPosition());
        testFloor=new Floor();
        testFloor.initializeFloor(20);
        for(int i = 10; i <=19; i++) {
            testFloor.getFloor()[10][i]=1;
        }
        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(floor.getFloor()[i][j],  testFloor.getFloor()[i][j]);
            }
        }

        //t47 -> (A2,B2,C1,D4)

        floor = new Floor();
        floor.initializeFloor(20);
        robot=new Robot(floor);
        robot.setCurrentPosition(new Position(10,10));
        Assertions.assertEquals(10,robot.getCurrentPosition().getxPosition());
        Assertions.assertEquals(10,robot.getCurrentPosition().getyPosition());
        robot.penDown();
        position=robot.moveForward(20);
        Assertions.assertEquals(10,position.getxPosition());
        Assertions.assertEquals(10,position.getyPosition());
        testFloor=new Floor();
        testFloor.initializeFloor(20);
        for(int i = 10; i <=10; i++) {
            testFloor.getFloor()[10][i]=1;
        }
        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(floor.getFloor()[i][j],  testFloor.getFloor()[i][j]);
            }
        }
        //t48 -> (A2,B2,C1,D5) covered by decoder

        // ==========================Pen Up Tests==============================
        //t49 -> (A1,B1,C4,D4)
        floor = new Floor();
        floor.initializeFloor(20);
        robot = new Robot(floor);
        robot.setCurrentPosition(new Position(0,19));
        Assertions.assertEquals(0,robot.getCurrentPosition().getxPosition());
        Assertions.assertEquals(19,robot.getCurrentPosition().getyPosition());
        robot.penUp();
        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.WEST,robot.getOrientation());
        position=robot.moveForward(23);
        Assertions.assertEquals(0,position.getxPosition());
        Assertions.assertEquals(19,position.getyPosition());
        int[][] map1 = floor.getFloor();

        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(map1[i][j], 0);
            }
        }

        //t50 -> (A1,B1,C1,D4)
        floor = new Floor();
        floor.initializeFloor(20);
        robot=new Robot(floor);
        robot.setCurrentPosition(new Position(0,19));
        Assertions.assertEquals(0,robot.getCurrentPosition().getxPosition());
        Assertions.assertEquals(19,robot.getCurrentPosition().getyPosition());
        robot.penUp();
        Assertions.assertEquals(Robot.Orientation.NORTH,robot.getOrientation());
        position=robot.moveForward(2);
        Assertions.assertEquals(0,position.getxPosition());
        Assertions.assertEquals(19,position.getyPosition());
        int[][] map2 = floor.getFloor();

        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(map2[i][j], 0);
            }
        }

        //t51 -> (A1,B1,C2,D4)
        floor = new Floor();
        floor.initializeFloor(20);
        robot=new Robot(floor);
        robot.setCurrentPosition(new Position(0,19));
        Assertions.assertEquals(0,robot.getCurrentPosition().getxPosition());
        Assertions.assertEquals(19,robot.getCurrentPosition().getyPosition());
        robot.penUp();
        robot.turnRight();
        robot.turnRight();
        Assertions.assertEquals(Robot.Orientation.SOUTH,robot.getOrientation());
        position=robot.moveForward(23);
        Assertions.assertEquals(0,position.getxPosition());
        Assertions.assertEquals(19,position.getyPosition());
        int[][] map3 = floor.getFloor();

        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(map3[i][j], 0);
            }
        }

        //t52 -> (A1,B1,C3,D4)
        floor = new Floor();
        floor.initializeFloor(20);
        robot=new Robot(floor);
        robot.setCurrentPosition(new Position(0,19));
        Assertions.assertEquals(0,robot.getCurrentPosition().getxPosition());
        Assertions.assertEquals(19,robot.getCurrentPosition().getyPosition());
        robot.penUp();
        robot.turnRight();
        Assertions.assertEquals(Robot.Orientation.EAST,robot.getOrientation());
        position=robot.moveForward(23);
        Assertions.assertEquals(0,position.getxPosition());
        Assertions.assertEquals(19,position.getyPosition());
        int[][] map4 = floor.getFloor();

        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(map4[i][j], 0);
            }
        }

        //t53 -> (A1,B1,C4,D1)
        floor = new Floor();
        floor.initializeFloor(20);
        robot=new Robot(floor);
        robot.setCurrentPosition(new Position(0,19));
        Assertions.assertEquals(0,robot.getCurrentPosition().getxPosition());
        Assertions.assertEquals(19,robot.getCurrentPosition().getyPosition());
        robot.penUp();
        robot.turnLeft();
        Assertions.assertEquals(Robot.Orientation.WEST,robot.getOrientation());
        position=robot.moveForward(0);
        Assertions.assertEquals(0,position.getxPosition());
        Assertions.assertEquals(19,position.getyPosition());
        int[][] map5 = floor.getFloor();

        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(map5[i][j], 0);
            }
        }

    }

}
