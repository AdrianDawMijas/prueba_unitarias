package org.iesvdm.tddjava.connect4;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


public class Connect4TDDSpec {

    private Connect4TDD tested;

    private OutputStream output;

    @BeforeEach
    public void beforeEachTest() {
        output = new ByteArrayOutputStream();

        //Se instancia el juego modificado para acceder a la salida de consola
        tested = new Connect4TDD(new PrintStream(output));
    }

    /*
     * The board is composed by 7 horizontal and 6 vertical empty positions
     */
    //Prueba
    @Test
    public void whenTheGameStartsTheBoardIsEmpty() {
        assertEquals(0, tested.getNumberOfDiscs());
    }

    /*
     * Players introduce discs on the top of the columns.
     * Introduced disc drops down the board if the column is empty.
     * Future discs introduced in the same column will stack over previous ones
     */

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {
        assertThrows(RuntimeException.class,() ->{
            tested.putDiscInColumn(8);
        });
    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {

        assertThat(tested.putDiscInColumn(0)).isEqualTo(0);

    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
        ass

    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() {



    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {



    }

    /*
     * It is a two-person game so there is one colour for each player.
     * One player uses red ('R'), the other one uses green ('G').
     * Players alternate turns, inserting one disc every time
     */

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {

    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen() {

    }

    /*
     * We want feedback when either, event or error occur within the game.
     * The output shows the status of the board on every move
     */

    @Test
    public void whenAskedForCurrentPlayerTheOutputNotice() {



    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {

    }

    /*
     * When no more discs can be inserted, the game finishes and it is considered a draw
     */

    @Test
    public void whenTheGameStartsItIsNotFinished() {

    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight vertical line then that player wins
     */

    @Test
    public void when4VerticalDiscsAreConnectedThenThatPlayerWins() {

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight horizontal line then that player wins
     */

    @Test
    public void when4HorizontalDiscsAreConnectedThenThatPlayerWins() {

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight diagonal line then that player wins
     */

    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins() {

    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins() {

    }
}
