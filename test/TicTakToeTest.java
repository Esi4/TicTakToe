import com.Esi4.TicTakToe.TicTakToe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTakToeTest {
    TicTakToe field = new TicTakToe(8);

    @Test
    void turnPlayer1() {
        field.crossX(2, 0);
        field.crossX(3, 4);
        assertEquals(TicTakToe.Field.X, field.returnCell(2, 0));
        assertEquals(TicTakToe.Field.X, field.returnCell(3, 4));
    }

    @Test
    void maxLengthVer() {
        field.crossX(2, 0);
        field.crossX(3, 0);
        field.crossX(4, 0);
        field.crossX(5, 0);
        field.crossX(6, 0);
       assertEquals(5, field.maxX(TicTakToe.Field.X));
    }

    @Test
    void maxLengthDioL() {
        field.crossX(1, 1);
        field.crossX(2, 2);
        field.crossX(3, 3);
        field.crossX(4, 4);
        field.crossX(5, 5);
        assertEquals(5, field.maxX(TicTakToe.Field.X));
    }

    @Test
    void maxLengthDioR() {
        field.crossX(1, 7);
        field.crossX(2, 6);
        field.crossX(3, 5);
        field.crossX(4, 4);
        field.crossX(5, 3);
        assertEquals(5, field.maxX(TicTakToe.Field.X));
    }

    @Test
    void cleanCell() {
        field.crossX(0, 0);
        field.crossX(0, 1);
        field.crossX(1, 0);
        field.crossX(1, 1);
        field.crossX(2, 0);
        field.crossX(2, 1);
        field.cleanCell(1,0);
        assertEquals(TicTakToe.Field.EmptyCell, field.returnCell(1,0));
    }
}