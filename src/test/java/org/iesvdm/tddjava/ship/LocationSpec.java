package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class LocationSpec {

    private final int x = 12;
    private final int y = 32;
    private final Direction direction = Direction.NORTH;
    private Point max;
    private Location location;
    private List<Point> obstacles;

    @BeforeMethod
    public void beforeTest() {
        max = new Point(50, 50);
        location = new Location(new Point(x, y), direction);
        obstacles = new ArrayList<Point>();
    }

    public void whenInstantiatedThenXIsStored() {
        assertEquals(location.getX(), x);
    }

    public void whenInstantiatedThenYIsStored() {
        assertEquals(location.getY(), y);
    }

    public void whenInstantiatedThenDirectionIsStored() {
        assertEquals(location.getDirection(), direction);
    }

    public void givenDirectionNWhenForwardThenYDecreases() {
            location.forward();
            assertEquals(location.getY(), y - 1);
    }

    public void givenDirectionSWhenForwardThenYIncreases() {
        location.setDirection(Direction.SOUTH);
        location.forward();
        assertEquals(location.getY(), y + 1);
    }

    public void givenDirectionEWhenForwardThenXIncreases() {
        location.setDirection(Direction.EAST);
        location.forward();
        assertEquals(location.getX(), x + 1);
    }

    public void givenDirectionWWhenForwardThenXDecreases() {
        location.setDirection(Direction.WEST);
        location.forward();
        assertEquals(location.getX(), x - 1);
    }

    public void givenDirectionNWhenBackwardThenYIncreases() {
        location.backward();
        assertEquals(location.getY(), y + 1);
    }

    public void givenDirectionSWhenBackwardThenYDecreases() {
        location.setDirection(Direction.SOUTH);
        location.backward();
        assertEquals(location.getY(), y - 1);
    }

    public void givenDirectionEWhenBackwardThenXDecreases() {
        location.setDirection(Direction.EAST);
        location.backward();
        assertEquals(location.getX(), x - 1);
    }

    public void givenDirectionWWhenBackwardThenXIncreases() {
        location.setDirection(Direction.WEST);
        location.backward();
        assertEquals(location.getX(), x + 1);
    }

    public void whenTurnLeftThenDirectionIsSet() {
        location.turnLeft();
        assertEquals(location.getDirection(), Direction.WEST);

    }

    public void whenTurnRightThenDirectionIsSet() {
        location.turnRight();
        assertEquals(location.getDirection(), Direction.EAST);
    }

    public void givenSameObjectsWhenEqualsThenTrue() {
            assertTrue(location.copy().equals(location));
    }

    public void givenDifferentObjectWhenEqualsThenFalse() {
        Location locationCopia = new Location(new Point(x+1, y), direction);
        assertFalse(location.equals(locationCopia));
    }

    public void givenDifferentXWhenEqualsThenFalse() {
        Location locationCopia = new Location(new Point(x+1, y), direction);
        assertFalse(location.equals(locationCopia));
    }

    public void givenDifferentYWhenEqualsThenFalse() {
        Location locationCopia = new Location(new Point(x+1, y), direction);
        assertFalse(location.equals(locationCopia));
    }

    public void givenDifferentDirectionWhenEqualsThenFalse() {
        Location locationCopia = new Location(new Point(x+1, y), direction);
        assertFalse(location.equals(locationCopia));
    }

    public void givenSameXYDirectionWhenEqualsThenTrue() {
        Location locationCopia = new Location(new Point(x, y), direction);
        assertTrue(location.equals(locationCopia));
    }

    public void whenCopyThenDifferentObject() {
        assertNotSame(location, location.copy());
        }

    public void whenCopyThenEquals() {
        assertEquals(location, location.copy());

    }

    public void givenDirectionEAndXEqualsMaxXWhenForwardThen1() {
        location.setDirection(Direction.EAST);
        location.getPoint().setX(max.getX());
        location.forward(max);
        assertEquals(location.getX(), 1);
    }

    public void givenDirectionWAndXEquals1WhenForwardThenMaxX() {
        location.setDirection(Direction.WEST);
        location.getPoint().setX(1);
        location.forward(max);
        assertEquals(location.getX(), max.getX());
    }

    public void givenDirectionNAndYEquals1WhenForwardThenMaxY() {
        location.setDirection(Direction.NORTH);
        location.getPoint().setY(1);
        location.forward(max);
        assertEquals(location.getY(), max.getY());
    }

    public void givenDirectionSAndYEqualsMaxYWhenForwardThen1() {
        location.setDirection(Direction.SOUTH);
        location.getPoint().setY(max.getY());
        location.forward(max);
        assertEquals(location.getY(), 1);
    }

    public void givenObstacleWhenForwardThenReturnFalse() {
        location.setDirection(Direction.NORTH);
        Point lugar = new Point(0,0);
        lugar.setX(location.getX());
        lugar.setY(location.getY()-1);
        obstacles.add(lugar);
        assertFalse(location.forward(max,obstacles));
    }

    public void givenObstacleWhenBackwardThenReturnFalse() {
        location.setDirection(Direction.NORTH);
        Point lugar = new Point(0,0);
        lugar.setX(location.getX());
        lugar.setY(location.getY()+1);
        obstacles.add(lugar);
        assertFalse(location.backward(max,obstacles));
    }

}
