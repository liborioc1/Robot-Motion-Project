import com.gradle.robotmotion.Floor;
import com.gradle.robotmotion.Position;
import com.gradle.robotmotion.Robot;
import com.gradle.robotmotion.CommandsDecoder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandsDecoderTests {
    static Floor floor;
    static Robot robot;
    static CommandsDecoder commandsDecoder;
    static AtomicBoolean flag=new AtomicBoolean(true);

    @BeforeAll
    public static void setUp()
    {
        floor = new Floor();
        robot=new Robot(floor);
        commandsDecoder=new CommandsDecoder(robot,floor,flag);
        //floor.initializeFloor(10);
    }

    @Test
    public void testDecodeCommand()
    {
        //TODO test negative, decimal etc

        ByteArrayOutputStream outContent3 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent3));
        commandsDecoder.decodeCommand("m 3");
        commandsDecoder.decodeCommand("s 2");
        commandsDecoder.decodeCommand("r");
        String expectedOutput3  = "Floor has not been initialized. Floor must be initialized to perform requested move\r\nInvalid Input Format. Please Check Spaces\r\nFloor has not been initialized. Floor must be initialized to perform requested move\r\n";
        assertEquals(expectedOutput3, outContent3.toString());

        commandsDecoder.decodeCommand("q");
        Assertions.assertFalse(flag.get());

        commandsDecoder.decodeCommand("i 10");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        commandsDecoder.decodeCommand("p");
        String expectedOutput  =
                "Floor is of size: 10 x 10\r\n" +
                        "9                     \r\n" +
                        "8                     \r\n" +
                        "7                     \r\n" +
                        "6                     \r\n" +
                        "5                     \r\n" +
                        "4                     \r\n" +
                        "3                     \r\n" +
                        "2                     \r\n" +
                        "1                     \r\n" +
                        "0                     \r\n" +
                        "  0 1 2 3 4 5 6 7 8 9 \r\n";
        assertEquals(expectedOutput, outContent.toString());
        Assertions.assertEquals(10,floor.getFloorSize());

        commandsDecoder.decodeCommand("m 2");
        Position position=new Position(0,2);
        Assertions.assertEquals(position.toString(),robot.getCurrentPosition().toString());

        commandsDecoder.decodeCommand("u");
        Assertions.assertTrue(robot.getPenUp());

        commandsDecoder.decodeCommand("d");
        Assertions.assertFalse(robot.getPenUp());

        commandsDecoder.decodeCommand("r");
        Assertions.assertEquals(Robot.Orientation.EAST,robot.getOrientation());
        commandsDecoder.decodeCommand("l");
        Assertions.assertEquals(Robot.Orientation.NORTH,robot.getOrientation());

        commandsDecoder.decodeCommand("c");
        
        ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent2));
        commandsDecoder.decodeCommand("invalid command");
        commandsDecoder.decodeCommand("s");

        String expectedOutput2  = "Invalid Input Format. Please Check Spaces\r\nInvalid Input Format. Please Check Spaces\r\n";
        assertEquals(expectedOutput2, outContent2.toString());

    }

    @AfterAll
    public static void teardown()
    {
        floor = null;
        robot=null;
        commandsDecoder=null;
    }

}
