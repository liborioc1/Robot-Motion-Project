import com.gradle.robotmotion.Floor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloorTests {


    @Test
    public void testInitializeFloor(){

        //t12
        Floor floor = new Floor();
        floor.initializeFloor(10);
        assertEquals(10, floor.getFloorSize());

        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(0, floor.getFloor()[i][j]);
            }
        }
        //t13
        floor = new Floor();
        floor.initializeFloor(0);
        assertEquals(0, floor.getFloorSize());

        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(0, floor.getFloor()[i][j]);
            }
        }
    }

    @Test
    public void testGetFloor(){
        //t14
        Floor floor = new Floor();
        floor.initializeFloor(3);
        int[][] map = new int[3][3];

        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                assertEquals(map[i][j], floor.getFloor()[i][j]);
            }
        }

    }

    @Test
    public void testGetFloorSize(){
        //t15
        Floor floor = new Floor();
        assertEquals(0, floor.getFloorSize());
    }


    @Test
    public void printTest(){
        //t16
        Floor floor = new Floor();
        floor.initializeFloor(10);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        floor.printFloor();
        String expectedOutput  =
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
        assertEquals(expectedOutput, outContent.toString());

        //t17
        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                floor.getFloor()[i][j] = 1;
            }
        }
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        floor.printFloor();
        expectedOutput  =
                        "Floor is of size: 10 x 10\r\n" +
                        "9   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "8   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "7   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "6   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "5   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "4   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "3   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "2   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "1   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "0   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "    0  1  2  3  4  5  6  7  8  9  \r\n";
        assertEquals(expectedOutput, outContent.toString());

        //t18
        for(int i = 0; i <= 4; i++){
            for(int j = 0; j <= 2; j++){
                floor.getFloor()[i][j] = 0;
            }
        }
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        floor.printFloor();
        expectedOutput  =
                "Floor is of size: 10 x 10\r\n" +
                        "9   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "8   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "7   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "6   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "5   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "4   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "3   *  *  *  *  *  *  *  *  *  *  \r\n" +
                        "2                  *  *  *  *  *  \r\n" +
                        "1                  *  *  *  *  *  \r\n" +
                        "0                  *  *  *  *  *  \r\n" +
                        "    0  1  2  3  4  5  6  7  8  9  \r\n";
        assertEquals(expectedOutput, outContent.toString());

        //t19
        floor = new Floor();
        floor.initializeFloor(15);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        floor.printFloor();
        expectedOutput  =
                "Floor is of size: 15 x 15\r\n" +
                        "14                                                    \r\n" +
                        "13                                                    \r\n" +
                        "12                                                    \r\n" +
                        "11                                                    \r\n" +
                        "10                                                    \r\n" +
                        "9                                                     \r\n" +
                        "8                                                     \r\n" +
                        "7                                                     \r\n" +
                        "6                                                     \r\n" +
                        "5                                                     \r\n" +
                        "4                                                     \r\n" +
                        "3                                                     \r\n" +
                        "2                                                     \r\n" +
                        "1                                                     \r\n" +
                        "0                                                     \r\n" +
                        "    0  1  2  3  4  5  6  7  8  9  10  11  12  13  14  \r\n";
        assertEquals(expectedOutput, outContent.toString());

        //t20
        for(int i = 0; i <= floor.getFloorSize() - 1; i++){
            for(int j = 0; j <= floor.getFloorSize() - 1; j++){
                floor.getFloor()[i][j] = 1;
            }
        }
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        floor.printFloor();
        expectedOutput  =
                "Floor is of size: 15 x 15\r\n" +
                        "14  *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "13  *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "12  *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "11  *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "10  *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "9   *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "8   *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "7   *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "6   *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "5   *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "4   *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "3   *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "2   *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "1   *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "0   *  *  *  *  *  *  *  *  *  *  *   *   *   *   *   \r\n" +
                        "    0  1  2  3  4  5  6  7  8  9  10  11  12  13  14  \r\n";
        assertEquals(expectedOutput, outContent.toString());


    }

}
