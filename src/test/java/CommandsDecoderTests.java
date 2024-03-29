import com.gradle.robotmotion.Floor;
import com.gradle.robotmotion.Position;
import com.gradle.robotmotion.Robot;
import com.gradle.robotmotion.CommandsDecoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandsDecoderTests {
    Floor floor;
    Robot robot;
    CommandsDecoder commandsDecoder;
    static AtomicBoolean flag=new AtomicBoolean(true);
    public static List<String> commandsList = Collections.synchronizedList(new ArrayList<String>());

    @Test
    public void testDecodeCommand()
    {
        floor = new Floor();
        robot=new Robot(floor);
        commandsDecoder=new CommandsDecoder(robot,floor,flag);

        //t0
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        commandsDecoder.decodeCommand("m 3");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());
        commandsDecoder.decodeCommand("s 2");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());
        commandsDecoder.decodeCommand("r");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());
        //check 3 prints of floor must be initialized
        String expectedOutput  = "Floor has not been initialized. Floor must be initialized to perform requested move\r\nInvalid Input Format. Please Check Spaces\r\nFloor has not been initialized. Floor must be initialized to perform requested command\r\n";
        assertEquals(expectedOutput, outContent.toString());

        //t8
        //check that quit sets q
        commandsDecoder.decodeCommand("q");
        Assertions.assertFalse(flag.get());
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());

        //t1
        //check board initialization and print
        commandsDecoder.decodeCommand("i 10");
        commandsList.add("i 10");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());
        ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent1));
        commandsDecoder.decodeCommand("p");
        String expectedOutput1  =
                "Floor is of size: 10 x 10\r\n" +
                        "9                                 \r\n" +
                        "8                                 \r\n" +
                        "7                                 \r\n" +
                        "6                                 \r\n" +
                        "5                                 \r\n" +
                        "4                                 \r\n" +
                        "3                                 \r\n" +
                        "2                                 \r\n" +
                        "1                                 \r\n" +
                        "0                                 \r\n" +
                        "    0  1  2  3  4  5  6  7  8  9  \r\n";
        assertEquals(expectedOutput1, outContent1.toString());
        Assertions.assertEquals(10,floor.getFloorSize());
        commandsList.add("p");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());

        //t55
        //test move decoding
        commandsDecoder.decodeCommand("m 2");
        Position position=new Position(0,2);
        Assertions.assertEquals(position.toString(),robot.getCurrentPosition().toString());
        commandsList.add("m 2");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());
        System.out.println(outContent1.toString());
        //t2
        commandsDecoder.decodeCommand("u");
        Assertions.assertTrue(robot.getPenUp());
        commandsList.add("u");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());

        //t3
        commandsDecoder.decodeCommand("d");
        Assertions.assertFalse(robot.getPenUp());
        commandsList.add("d");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());

        //t4
        commandsDecoder.decodeCommand("r");
        Assertions.assertEquals(Robot.Orientation.EAST,robot.getOrientation());
        commandsList.add("r");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());

        //t5
        commandsDecoder.decodeCommand("l");
        Assertions.assertEquals(Robot.Orientation.NORTH,robot.getOrientation());
        commandsList.add("l");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());

        //t6
        ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent2));
        commandsDecoder.decodeCommand("c");
        String expectedOutput2  =commandsDecoder.getRobot().printCurrentPosition()+"\r\n";
        assertEquals(expectedOutput2, outContent2.toString());
        commandsList.add("c");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());

        //t10
        //t11
        ByteArrayOutputStream outContent3 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent3));
        commandsDecoder.decodeCommand("m -20");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());
        commandsDecoder.decodeCommand("m 60");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());
        commandsDecoder.decodeCommand("invalid command");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());
        commandsDecoder.decodeCommand("s");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());
        String expectedOutput3  = "Invalid Input Format or Incorrect Command or Value(must be <= 40)\r\nInvalid Input Format or Incorrect Command or Value(must be <= 40)\r\nInvalid Input Format or Incorrect Command or Value(must be <= 40)\r\nInvalid Input Format. Please Check Spaces\r\n";
        assertEquals(expectedOutput3, outContent3.toString());

        //t54
        commandsDecoder.decodeCommand("i 3");
        outContent1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent1));
        commandsList.add("i 3");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());

        //t9
        commandsDecoder.decodeCommand("p");
        commandsList.add("p");
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());
        String expectedOutput4  =
                "Floor is of size: 3 x 3\r\n" +
                        "2            \r\n" +
                        "1            \r\n" +
                        "0            \r\n" +
                        "    0  1  2  \r\n";
        assertEquals(expectedOutput4, outContent1.toString());
        Assertions.assertEquals(3,floor.getFloorSize());
        position=new Position(0,0);
        Assertions.assertEquals(position.toString(),commandsDecoder.getRobot().getCurrentPosition().toString());
        Assertions.assertEquals(Robot.Orientation.NORTH, commandsDecoder.getRobot().getOrientation());
        Assertions.assertTrue(commandsDecoder.getRobot().getPenUp());


        //check list at the end
        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());

        Position prvsPosition= robot.getCurrentPosition();
        Robot.Orientation prvsOrientation= robot.getOrientation();
        boolean prvsPen=robot.getPenUp();

        //t56
        commandsDecoder.decodeCommand("h");

        Assertions.assertEquals(robot.getOrientation(),prvsOrientation);
        Assertions.assertEquals(robot.getPenUp(), prvsPen);
        Assertions.assertEquals(robot.getCurrentPosition().getxPosition(), prvsPosition.getxPosition());
        Assertions.assertEquals(robot.getCurrentPosition().getyPosition(), prvsPosition.getyPosition());

    }

    @Test
    public void testDecodeReplayCommands()
    {

        floor = new Floor();
        robot=new Robot(floor);
        commandsDecoder=new CommandsDecoder(robot,floor,flag);

        //t1
        //check board initialization and print
        commandsDecoder.decodeReplayCommands("i 10");
        ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent1));
        commandsDecoder.decodeReplayCommands("p");
        String expectedOutput1  =
                "Floor is of size: 10 x 10\r\n" +
                        "9                                 \r\n" +
                        "8                                 \r\n" +
                        "7                                 \r\n" +
                        "6                                 \r\n" +
                        "5                                 \r\n" +
                        "4                                 \r\n" +
                        "3                                 \r\n" +
                        "2                                 \r\n" +
                        "1                                 \r\n" +
                        "0                                 \r\n" +
                        "    0  1  2  3  4  5  6  7  8  9  \r\n";
        assertEquals(expectedOutput1, outContent1.toString());
        Assertions.assertEquals(10,floor.getFloorSize());

        //t55
        //test move decoding
        commandsDecoder.decodeReplayCommands("m 2");
        Position position=new Position(0,2);
        Assertions.assertEquals(position.toString(),robot.getCurrentPosition().toString());

        //t2
        commandsDecoder.decodeReplayCommands("u");
        Assertions.assertTrue(robot.getPenUp());

        //t3
        commandsDecoder.decodeReplayCommands("d");
        Assertions.assertFalse(robot.getPenUp());

        //t4
        commandsDecoder.decodeReplayCommands("r");
        Assertions.assertEquals(Robot.Orientation.EAST,robot.getOrientation());
        //t5
        commandsDecoder.decodeReplayCommands("l");
        Assertions.assertEquals(Robot.Orientation.NORTH,robot.getOrientation());

        //t6
        ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent2));
        commandsDecoder.decodeReplayCommands("c");
        String expectedOutput2  =commandsDecoder.getRobot().printCurrentPosition()+"\r\n";
        assertEquals(expectedOutput2, outContent2.toString());

        //t54
        commandsDecoder.decodeReplayCommands("i 3");
        outContent1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent1));
        //t9
        commandsDecoder.decodeReplayCommands("p");
        String expectedOutput4  =
                "Floor is of size: 3 x 3\r\n" +
                        "2            \r\n" +
                        "1            \r\n" +
                        "0            \r\n" +
                        "    0  1  2  \r\n";
        assertEquals(expectedOutput4, outContent1.toString());
        Assertions.assertEquals(3,floor.getFloorSize());
        position=new Position(0,0);
        Assertions.assertEquals(position.toString(),commandsDecoder.getRobot().getCurrentPosition().toString());
        Assertions.assertEquals(Robot.Orientation.NORTH, commandsDecoder.getRobot().getOrientation());
        Assertions.assertTrue(commandsDecoder.getRobot().getPenUp());
    }

    @Test
    public void testReplayCommands(){

        floor = new Floor();
        robot=new Robot(floor);
        commandsDecoder=new CommandsDecoder(robot,floor,flag);

        commandsDecoder.decodeReplayCommands("i 10");
        commandsDecoder.decodeReplayCommands("p");
        commandsDecoder.decodeReplayCommands("m 2");
        commandsDecoder.decodeReplayCommands("u");
        commandsDecoder.decodeReplayCommands("d");
        commandsDecoder.decodeReplayCommands("r");
        commandsDecoder.decodeReplayCommands("l");
        commandsDecoder.decodeReplayCommands("c");
        commandsDecoder.decodeReplayCommands("i 3");
        commandsDecoder.decodeReplayCommands("p");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Floor floorPrvs = floor;
        Position prvsPosition= robot.getCurrentPosition();
        Robot.Orientation prvsOrientation= robot.getOrientation();
        boolean prvsPen=robot.getPenUp();

        commandsDecoder.replayCommands();

        Assertions.assertArrayEquals(Robot.historyOfCommands.toArray(), commandsList.toArray());
        Assertions.assertEquals(robot.getOrientation(),prvsOrientation);
        Assertions.assertEquals(robot.getPenUp(), prvsPen);
        Assertions.assertEquals(robot.getCurrentPosition().getxPosition(), prvsPosition.getxPosition());
        Assertions.assertEquals(robot.getCurrentPosition().getyPosition(), prvsPosition.getyPosition());

        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(floor.getFloor()[i][j],  floorPrvs.getFloor()[i][j]);
            }
        }

        String expectedOutput  = "\nReplaying: \r\nCommand: i 10\r\nCommand: p\r\n" +
                "Floor is of size: 10 x 10\r\n" +
                "9                                 \r\n" +
                "8                                 \r\n" +
                "7                                 \r\n" +
                "6                                 \r\n" +
                "5                                 \r\n" +
                "4                                 \r\n" +
                "3                                 \r\n" +
                "2                                 \r\n" +
                "1                                 \r\n" +
                "0                                 \r\n" +
                "    0  1  2  3  4  5  6  7  8  9  \r\n" +
                "Command: m 2\r\n" +
                "Command: u\r\n" +
                "Command: d\r\n" +
                "Command: r\r\n" +
                "Command: l\r\n" +
                "Command: c\r\n" +
                "Position: {0,2} - Pen: Down - Facing: NORTH\r\n" +
                "Command: i 3\r\n" +
                "Command: p\r\n" +
                "Floor is of size: 3 x 3\r\n" +
                "2            \r\n" +
                "1            \r\n" +
                "0            \r\n" +
                "    0  1  2  \r\n" +
                "End Replay.\n\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

}
