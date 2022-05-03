package pl.first.firstjava;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoxTest {
    @Test
    void SudokuRowVerify() {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i, new SudokuField());
            try {
                fields.get(i).setValue(i);
            } catch (InvalidValueFieldException e) {
                e.printStackTrace();
            }
        }
        SudokuBox box = null;
        try {
            box = new SudokuBox(fields);
        } catch (InvalidSizeObjectException e) {

        }
        assertTrue(box.verify());
    }

    @Test
    void toStringTest() {
        String expected = "SudokuBox{fields=[SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}]}";
        SudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(solver1);
        SudokuSolver solver2 = new BacktrackingSudokuSolver();
        SudokuBoard board2 = new SudokuBoard(solver2);
        try {
            assertEquals(expected, board1.getBox(0, 0).toString());
            assertEquals(expected, board2.getBox(4, 3).toString());
            try {
                board2.solveGame();
            } catch (NoSolution noSolution) {
                noSolution.printStackTrace();
            }
            assertNotEquals(board2.getBox(4, 3).toString(), expected);
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void hashCodeTest() {
        SudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(solver1);
        SudokuSolver solver2 = new BacktrackingSudokuSolver();
        SudokuBoard board2 = new SudokuBoard(solver2);
        try {
            assertEquals(board1.getBox(0, 0).hashCode(), board1.getBox(0, 0).hashCode());
            assertEquals(board1.getBox(0, 0).hashCode(), board2.getBox(0, 0).hashCode());
            try {
                board2.solveGame();
            } catch (NoSolution noSolution) {
                noSolution.printStackTrace();
            }
            assertNotEquals(board1.getBox(0, 0).hashCode(), board2.getBox(0, 0).hashCode());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void equalsTest() {
        SudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(solver1);
        SudokuSolver solver2 = new BacktrackingSudokuSolver();
        SudokuBoard board2 = new SudokuBoard(solver2);
        try {
            assertTrue(board1.getBox(0, 0).equals(board2.getBox(1, 1)));
            assertTrue(board1.getBox(0, 0).equals(board2.getBox(0, 0)));
            assertTrue(board1.getBox(0, 0).equals(board1.getBox(3, 1)));
            assertTrue(board2.getBox(0, 0).equals(board2.getBox(0, 0)));
            try {
                board2.solveGame();
            } catch (NoSolution noSolution) {
                noSolution.printStackTrace();
            }
            assertFalse(board1.getBox(0, 0).equals(board2.getBox(0, 0)));
            assertFalse(board2.getBox(0, 0).equals(board1.getBox(0, 0)));
        } catch (Exception e ) {
            assertFalse(true);
        }
    }
}