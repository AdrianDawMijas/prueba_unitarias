package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipSpec {

    private Ship ship;
    private Location location;
    private Planet planet;

    @BeforeMethod
    public void beforeTest() {
        Point max = new Point(50, 50);
        location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        planet = new Planet(max, obstacles);
//        ship = new Ship(location);
        ship = new Ship(location, planet);
    }

    public void whenInstantiatedThenLocationIsSet() {
//        Location location = new Location(new Point(21, 13), Direction.NORTH);
//        Ship ship = new Ship(location);

    }

//    public void givenNorthWhenMoveForwardThenYDecreases() {
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getY(), 12);
//    }
//
//    public void givenEastWhenMoveForwardThenXIncreases() {
//        ship.getLocation().setDirection(Direction.EAST);
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getX(), 22);
//    }

    public void whenMoveForwardThenForward() {
        Location locationFinal= new Location(new Point(21, 12), Direction.NORTH);
        ship.moveForward();

      assertEquals(ship.getLocation(), locationFinal);
    }

    public void whenMoveBackwardThenBackward() {
        Location locationFinal= new Location(new Point(21, 14), Direction.NORTH);
        ship.moveBackward();

        assertEquals(ship.getLocation(), locationFinal);
    }

    public void whenTurnLeftThenLeft() {
        Location locationFinal= new Location(new Point(20, 13), Direction.NORTH);
        ship.turnLeft();

        assertEquals(ship.getLocation(), locationFinal);
    }

    public void whenTurnRightThenRight() {
        Location locationFinal= new Location(new Point(22, 13), Direction.NORTH);
        ship.turnRight();

        assertEquals(ship.getLocation(), locationFinal);
    }

    public void whenReceiveCommandsFThenForward() {
        Location locationFinal= new Location(new Point(21, 12), Direction.NORTH);
        ship.moveForward();

        assertEquals(ship.getLocation(), locationFinal);
    }

    public void whenReceiveCommandsBThenBackward() {
        Location locationFinal= new Location(new Point(21, 12), Direction.NORTH);
        ship.moveForward();

        assertEquals(ship.getLocation(), locationFinal);
    }

    public void whenReceiveCommandsLThenTurnLeft() {
        Location locationFinal= new Location(new Point(21, 12), Direction.NORTH);
        ship.moveForward();

        assertEquals(ship.getLocation(), locationFinal);

    }

    public void whenReceiveCommandsRThenTurnRight() {
        Location locationFinal= new Location(new Point(21, 12), Direction.NORTH);
        ship.moveForward();

        assertEquals(ship.getLocation(), locationFinal);

    }

    public void whenReceiveCommandsThenAllAreExecuted() {
            ship.receiveCommands("f");
            ship.receiveCommands("b");
            ship.receiveCommands("l");
            ship.receiveCommands("r");

            Location locationFinal= new Location(new Point(21, 13), Direction.NORTH);
            assertEquals(ship.getLocation(), locationFinal);
    }

    public void whenInstantiatedThenPlanetIsStored() {
      assertEquals(ship.getPlanet(), planet);
    }

    public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {
            ship.getLocation().setDirection(Direction.EAST);
            ship.getLocation().getPoint().setX(50);
            ship.receiveCommands("f");
            assertEquals(ship.getLocation().getX(), 1);

    }

    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {
        ship.getLocation().setDirection(Direction.EAST);
        ship.getLocation().getPoint().setX(1);
        ship.receiveCommands("b");
        assertEquals(ship.getLocation().getX(), 50);
    }

    public void whenReceiveCommandsThenStopOnObstacle() {
        ship.getLocation().getPoint().setX(44);
        ship.getLocation().getPoint().setY(45);
        assertFalse(ship.moveForward());

    }

    public void whenReceiveCommandsThenOForOkAndXForObstacle() {
        ship.getLocation().getPoint().setX(44);
        ship.getLocation().getPoint().setY(45);
        assertTrue(ship.receiveCommands("f").equals("X"));
        assertTrue(ship.receiveCommands("b").equals("O"));


    }

}
