package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;
import static org.testng.Assert.*;

@Test
public class DirectionSpec {

    public void whenGetFromShortNameNThenReturnDirectionN() {
            assertEquals(Direction.getFromShortName('N'), Direction.NORTH);
    }

    public void whenGetFromShortNameWThenReturnDirectionW() {
        assertEquals(Direction.getFromShortName('W'), Direction.WEST);
    }

    public void whenGetFromShortNameBThenReturnNone() {
        assertEquals(Direction.getFromShortName('B'), Direction.NONE);
    }

    public void givenSWhenLeftThenE() {
        Direction direction = Direction.getFromShortName('S');
        assertEquals(direction.turnLeft(), Direction.EAST);
    }

    public void givenNWhenLeftThenW() {
        Direction direction = Direction.getFromShortName('N');
        assertEquals(direction.turnLeft(), Direction.WEST);
    }

    public void givenSWhenRightThenW() {
        Direction direction = Direction.getFromShortName('S');
        assertEquals(direction.turnRight(), Direction.WEST);
    }

    public void givenWWhenRightThenN() {
        Direction direction = Direction.getFromShortName('W');
        assertEquals(direction.turnRight(), Direction.NORTH);
    }

}
