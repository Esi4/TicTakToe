import com.Esi4.TicTakToe.TicTakToe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTakToeTest {
    TicTakToe field = new TicTakToe(8);

    @Test
    void turnPlayer1() {
        field.turnPlayer1(2, 0);
        field.turnPlayer1(3, 4);
        assertEquals(TicTakToe.Field.X, field.returnCell(2, 0));
        assertEquals(TicTakToe.Field.X, field.returnCell(3, 4));
    }

    @Test
    void maxLengthVer() {
        field.turnPlayer1(2, 0);
        field.turnPlayer1(3, 0);
        field.turnPlayer1(4, 0);
        field.turnPlayer1(5, 0);
        field.turnPlayer1(6, 0);
        field.maxLength(TicTakToe.Field.X);
        assertEquals(5, field.maxX());
    }

    @Test
    void maxLengthDioL() {
        field.turnPlayer1(1, 1);
        field.turnPlayer1(2, 2);
        field.turnPlayer1(3, 3);
        field.turnPlayer1(4, 4);
        field.turnPlayer1(5, 5);
        field.maxLength(TicTakToe.Field.X);
        assertEquals(5, field.maxX());
    }

    @Test
    void maxLengthDioR() {
        field.turnPlayer1(1, 7);
        field.turnPlayer1(2, 6);
        field.turnPlayer1(3, 5);
        field.turnPlayer1(4, 4);
        field.turnPlayer1(5, 3);
        field.maxLength(TicTakToe.Field.X);
        assertEquals(5, field.maxX());
    }

    @Test
    void cleanCell() {
        field.turnPlayer1(0, 0);
        field.turnPlayer1(0, 1);
        field.turnPlayer1(1, 0);
        field.turnPlayer1(1, 1);
        field.turnPlayer1(2, 0);
        field.turnPlayer1(2, 1);
        field.cleanCell(1,0);
        assertEquals(TicTakToe.Field.EmptyCell, field.returnCell(1,0));
    }
}