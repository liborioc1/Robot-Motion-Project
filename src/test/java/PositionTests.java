import com.gradle.robotmotion.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTests {

    @Test
    public void testCopyConstructor(){
        Position position = new Position(4,5);
        Position newPosition = new Position(position);
        Assertions.assertNotEquals(position,newPosition);
        Assertions.assertEquals(position.getxPosition(), newPosition.getxPosition());
        Assertions.assertEquals(position.getyPosition(), newPosition.getyPosition());
    }

    @Test
    public void testToString(){
        Position position = new Position(4,5);
        assertEquals("{4,5}",position.toString());
    }

    @Test
    public void testGetYPosition(){
        Position position = new Position(4,5);
        assertEquals(5, position.getyPosition());
    }

    @Test
    public void testGetXPosition(){
        Position position = new Position(4,5);
        assertEquals(4, position.getxPosition());
    }

    @Test
    public void testSetXPosition(){
        Position position = new Position(4,5);
        position.setxPosition(8);
        assertEquals(8, position.getxPosition());

        position.setxPosition(3);
        assertEquals(3, position.getxPosition());
    }

    @Test
    public void testSetYPosition(){
        Position position = new Position(4,5);
        position.setyPosition(8);
        assertEquals(8, position.getyPosition());

        position.setyPosition(3);
        assertEquals(3, position.getyPosition());
    }

}
