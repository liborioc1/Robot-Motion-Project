import com.gradle.robotmotion.Floor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FloorTests {

    static Floor floor;

    @BeforeAll
    public static void setUp(){
        floor = new Floor();
        floor.initializeFloor(10);
    }

    @Test
    public void testGetFloorSize(){
        Assertions.assertEquals(10, floor.getFloorSize());
    }

    @Test
    public void testSetFloorSize(){
        floor.setFloorSize(5);
        Assertions.assertEquals(5, floor.getFloorSize());
    }

    @AfterAll
    public static void teardown(){
        floor = null;
    }

}
