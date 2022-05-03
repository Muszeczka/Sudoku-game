package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuRowTest {
    @Test
    void SudokuRowVerify() throws InvalidSizeObjectException {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i, new SudokuField());
            try {
                fields.get(i).setValue(i);
            } catch (InvalidValueFieldException e) {
                e.printStackTrace();
            }
        }
        SudokuRow row = new SudokuRow(fields);
        assertTrue(row.verify());
    }

    @Test
    void toStringTest() throws InvalidValueException {
        String expected = "SudokuRow{fields=[SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}]}";
        SudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(solver1);
        SudokuSolver solver2 = new BacktrackingSudokuSolver();
        SudokuBoard board2 = new SudokuBoard(solver2);
        assertEquals(expected, board1.getRow(0).toString());
        assertEquals(expected, board2.getRow(4).toString());
        try {
            board2.solveGame();
        } catch (NoSolution noSolution) {
            noSolution.printStackTrace();
        }
        assertNotEquals(board2.getRow(4).toString(), expected);
    }

    @Test
    void hashCodeTest() throws InvalidValueException {
        SudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(solver1);
        SudokuSolver solver2 = new BacktrackingSudokuSolver();
        SudokuBoard board2 = new SudokuBoard(solver2);
        assertEquals(board1.getRow(0).hashCode(),board1.getRow(0).hashCode());
        assertEquals(board1.getRow(0).hashCode(),board2.getRow(0).hashCode());
        try {
            board2.solveGame();
        } catch (NoSolution noSolution) {
            noSolution.printStackTrace();
        }
        assertNotEquals(board1.getRow(0).hashCode(),board2.getRow(0).hashCode());
    }

    @Test
    void equalsTest() throws InvalidValueException {
        SudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(solver1);
        SudokuSolver solver2 = new BacktrackingSudokuSolver();
        SudokuBoard board2 = new SudokuBoard(solver2);
        assertTrue(board1.getRow(0).equals(board2.getRow(1)));
        assertTrue(board1.getRow(0).equals(board2.getRow(0)));
        assertTrue(board1.getRow(0).equals(board1.getRow(3)));
        assertTrue(board2.getRow(0).equals(board2.getRow(0)));
        try {
            board2.solveGame();
        } catch (NoSolution noSolution) {
            noSolution.printStackTrace();
        }
        assertFalse(board1.getRow(0).equals(board2.getRow(0)));
        assertFalse(board2.getRow(0).equals(board1.getRow(0)));

        List<SudokuField> tab = Arrays.asList(new SudokuField[2]);
        assertThrows(InvalidSizeObjectException.class, ()-> new SudokuRow(tab));


    }

}