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
        assertThat(tested.putDiscInColumn(0)).isEqualTo(0);
        assertThat(tested.putDiscInColumn(0)).isEqualTo(1);

    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() {
        int numeroDiscosInicialmente = tested.getNumberOfDiscs();
        tested.putDiscInColumn(0);
        assertThat(numeroDiscosInicialmente).isLessThan(tested.getNumberOfDiscs());
    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {

        assertThat(tested.putDiscInColumn(0)).isEqualTo(0);
        assertThat(tested.putDiscInColumn(0)).isEqualTo(1);
        assertThat(tested.putDiscInColumn(0)).isEqualTo(2);
        assertThat(tested.putDiscInColumn(0)).isEqualTo(3);
        assertThat(tested.putDiscInColumn(0)).isEqualTo(4);
        assertThat(tested.putDiscInColumn(0)).isEqualTo(5);

            assertThrows(RuntimeException.class,() ->{
                tested.putDiscInColumn(0);
            });

    }

    /*
     * It is a two-person game so there is one colour for each player.
     * One player uses red ('R'), the other one uses green ('G').
     * Players alternate turns, inserting one disc every time
     */

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {
        assertThat(tested.getCurrentPlayer()).isEqualTo("R");
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen() {
        assertThat(tested.getCurrentPlayer()).isEqualTo("R");
        assertThat(tested.putDiscInColumn(0)).isEqualTo(0);
        assertThat(tested.getCurrentPlayer()).isEqualTo("G");
    }

    /*
     * We want feedback when either, event or error occur within the game.
     * The output shows the status of the board on every move
     */

    @Test
    public void whenAskedForCurrentPlayerTheOutputNotice() {
        ByteArrayOutputStream by
        tested.getCurrentPlayer();
        assertThat(byteArrayOutputStream.toString()).isNotEmpty();

    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {

    }

    /*
     * When no more discs can be inserted, the game finishes and it is considered a draw
     */

    @Test
    public void whenTheGameStartsItIsNotFinished() {
            assertFalse(tested.isFinished());
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
        assertThat(tested.putDiscInColumn(0)).isEqualTo(0);
        assertThat(tested.putDiscInColumn(1)).isEqualTo(0);
        assertThat(tested.putDiscInColumn(0)).isEqualTo(1);
        assertThat(tested.putDiscInColumn(1)).isEqualTo(1);
        assertThat(tested.putDiscInColumn(0)).isEqualTo(2);
        assertThat(tested.putDiscInColumn(1)).isEqualTo(2);
        assertThat(tested.putDiscInColumn(0)).isEqualTo(3);
        assertThat(tested.getWinner()).isEqualTo("R");
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight horizontal line then that player wins
     */

    @Test
    public void when4HorizontalDiscsAreConnectedThenThatPlayerWins() {
        assertThat(tested.putDiscInColumn(0)).isEqualTo(0);
        assertThat(tested.putDiscInColumn(0)).isEqualTo(1);
        assertThat(tested.putDiscInColumn(1)).isEqualTo(0);
        assertThat(tested.putDiscInColumn(1)).isEqualTo(1);
        assertThat(tested.putDiscInColumn(2)).isEqualTo(0);
        assertThat(tested.putDiscInColumn(1)).isEqualTo(2);
        assertThat(tested.putDiscInColumn(3)).isEqualTo(0);
        assertThat(tested.getWinner()).isEqualTo("R");
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight diagonal line then that player wins
     */

    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins() {
        assertThat(tested.putDiscInColumn(0)).isEqualTo(0);//R
        assertThat(tested.putDiscInColumn(1)).isEqualTo(0);//G
        assertThat(tested.putDiscInColumn(1)).isEqualTo(1);//R
        assertThat(tested.putDiscInColumn(2)).isEqualTo(0);//G
        assertThat(tested.putDiscInColumn(2)).isEqualTo(1);//R
        assertThat(tested.putDiscInColumn(3)).isEqualTo(0);//G
        assertThat(tested.putDiscInColumn(2)).isEqualTo(2);//R
        assertThat(tested.putDiscInColumn(3)).isEqualTo(1);//G
        assertThat(tested.putDiscInColumn(3)).isEqualTo(2);//R
        assertThat(tested.putDiscInColumn(2)).isEqualTo(3);//G
        assertThat(tested.putDiscInColumn(3)).isEqualTo(3);//R
        assertThat(tested.getWinner()).isEqualTo("R");
    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins() {
        assertThat(tested.putDiscInColumn(4)).isEqualTo(0);//R
        assertThat(tested.putDiscInColumn(3)).isEqualTo(0);//G
        assertThat(tested.putDiscInColumn(3)).isEqualTo(1);//R
        assertThat(tested.putDiscInColumn(2)).isEqualTo(0);//G
        assertThat(tested.putDiscInColumn(2)).isEqualTo(1);//R
        assertThat(tested.putDiscInColumn(1)).isEqualTo(0);//G
        assertThat(tested.putDiscInColumn(2)).isEqualTo(2);//R
        assertThat(tested.putDiscInColumn(1)).isEqualTo(1);//G
        assertThat(tested.putDiscInColumn(1)).isEqualTo(2);//R
        assertThat(tested.putDiscInColumn(2)).isEqualTo(3);//G
        assertThat(tested.putDiscInColumn(1)).isEqualTo(3);//R
        assertThat(tested.getWinner()).isEqualTo("R");

    }
}
