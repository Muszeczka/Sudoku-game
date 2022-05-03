package pl.first.firstjava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    private int [][] board;
    int[][] board2;
    int[][] board3;
    int[][] board4;
    List<SudokuField>  fields;
    List<SudokuField> fields1;
    List<SudokuField> fields2;
    List<SudokuField> fields4;
    SudokuSolver solver0;
    SudokuBoard sudoku0;
    SudokuSolver solver1;
    SudokuBoard sudoku1;
    SudokuSolver solver2;
    SudokuBoard sudoku2;
    SudokuSolver solver3;
    SudokuBoard emptySudoku;
    SudokuSolver solver4;
    SudokuBoard sudoku4;
    SudokuSolver emptySolver2;
    SudokuBoard emptySudoku2;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SudokuBoardTest.class);

    @BeforeEach
    public void initialize() {
        board = new int[][]{{0, 5, 3, 1, 7, 4, 9, 8, 2}, {1, 2, 4, 3, 8, 9, 5, 6, 7}, {7, 8, 9, 2, 5, 6, 1, 3, 4},
                {2, 1, 5, 4, 3, 7, 6, 9, 8}, {3, 4, 6, 5, 9, 8, 2, 7, 1}, {8, 9, 7, 6, 1, 2, 3, 4, 5},
                {4, 3, 1, 7, 6, 5, 8, 2, 9}, {5, 7, 8, 9, 2, 3, 4, 1, 6}, {9, 6, 2, 8, 4, 1, 7, 5, 3}};
        board2 = new int[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9}, {1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1}};
        board3 = new int[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9}, {3, 1, 1, 1, 1, 1, 1, 1, 1}, {4, 1, 1, 1, 1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1, 1, 1, 1, 1}, {5, 1, 1, 1, 1, 1, 1, 1, 1}, {6, 1, 1, 1, 1, 1, 1, 1, 1},
                {7, 1, 1, 1, 1, 1, 1, 1, 1}, {8, 1, 1, 1, 1, 1, 1, 1, 1}, {9, 1, 1, 1, 1, 1, 1, 1, 1}};
        board4 = new int[][]{{1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1}};
        fields = Arrays.asList(new SudokuField[81]);
        fields1 = Arrays.asList(new SudokuField[81]);
        fields2 = Arrays.asList(new SudokuField[81]);
        fields4 = Arrays.asList(new SudokuField[81]);
        for (int j = 0, i=0; j < 9; j++) {
            try {

                    fields.set(j, new SudokuField());
                    fields.get(j).setValue(board[i][j]);
                    fields1.set(j, new SudokuField());
                    fields1.get(j).setValue(board2[i][j]);
                    fields2.set(j, new SudokuField());
                    fields2.get(j).setValue(board3[i][j]);
                    fields4.set(j, new SudokuField());
                    fields4.get(j).setValue(board4[i][j]);
                    i=+9;

            } catch (Exception e) {

            }
        }
        solver0 = new BacktrackingSudokuSolver();
        sudoku0= new SudokuBoard(fields, solver0);
        solver3 = new BacktrackingSudokuSolver();
        emptySudoku = new SudokuBoard(solver3);
        solver1 = new BacktrackingSudokuSolver();
        sudoku1 = new SudokuBoard(fields1, solver1);
        solver2 = new BacktrackingSudokuSolver();
        sudoku2 = new SudokuBoard(fields2, solver2);
        solver4 = new BacktrackingSudokuSolver();
        sudoku4 = new SudokuBoard(fields4, solver4);
        emptySolver2 = new BacktrackingSudokuSolver();
        emptySudoku2 = new SudokuBoard(emptySolver2);
    }

    @Test
    void getBoardTest() {
        Random rand=new Random();
        int rand_number1,rand_number2;
        for(int i=0;i<162;i++){
            rand_number1=rand.nextInt(8)+1;
            rand_number2=rand.nextInt(8)+1;
            assertEquals(board[rand_number1][rand_number2],sudoku0.get(rand_number1,rand_number2));
            assertEquals(board[rand_number2][rand_number1],sudoku0.get(rand_number2,rand_number1));
        }
    }

    @Test
    void SudokuBoardConstructorsTest() {
        for(int i = 0; i<9;i++) {
            for(int j = 0; j<9; j++) {
                assertEquals(emptySudoku.get(i,j),0);
                assertEquals(sudoku0.get(i,j), board[i][j]);
            }
        }
    }

    @Test
    void sudokuSolveTest() {
        assertThrows(NoSolution.class , ()->sudoku4.solveGame() );
        assertThrows(NoSolution.class , ()->sudoku1.solveGame() );
        NoSolution message = assertThrows(NoSolution.class , ()->sudoku2.solveGame() );
        assertTrue(message.getMessage().contains("No solution"));
        assertDoesNotThrow(()-> emptySudoku.solveGame());
        System.out.println(emptySudoku.showSudoku());

    }
    @Test
    void showSudokuTest() {
        String tmp = "";
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku = new SudokuBoard(solver);
        for (int j = 0;j < 9;j++) {
            tmp += "0 0 0 0 0 0 0 0 0 ";
            tmp += ",";
        }
        assertTrue(sudoku.showSudoku().equals(tmp));
    }

    @Test
    void setBoardTest() {
        for (int i = 0; i < 9;i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    sudoku0.set(i, j, i);
                } catch (InvalidValueException e) {

                }
                assertEquals(sudoku0.get(i, j), i);
            }
        }
    }

    @Test
    void getColumn_getRow_getBoxTest() throws CloneNotSupportedException {
        try {
            emptySudoku.solveGame();
        } catch (NoSolution noSolution) {
        }
        try {
            for (int i = 0; i < 9; i++) {
                assertNotEquals(emptySudoku.getColumn(i), null);
                assertNotEquals(emptySudoku.getRow(i), null);
                for (int j = 0; j < 9; j++) {
                    assertNotEquals(emptySudoku.getBox(i, j), null);
                }
            }
            SudokuObject object = emptySudoku.getColumn(1);
            SudokuColumn cloneColumn = (SudokuColumn) object.clone();
            for (int i = 0; i < 9; i++) {
                assertEquals(cloneColumn.get(i), object.get(i));
            }
        } catch (Exception e ) {
            assertFalse(true);
        }
    }

    @Test
    void observableTest() {
        for (int i = 0; i < 2; i++ ) {
            Observer ob1 = new ChangeWatcher(emptySudoku, true);
            Observer ob2 = new ChangeWatcher(emptySudoku, false);
            assertEquals(emptySudoku.getAmountObservers(), 0);
            emptySudoku.attach(ob1);
            assertEquals(emptySudoku.getAmountObservers(), 1);
            try {
                emptySudoku.set(0, 0, 1);
            } catch (InvalidValueException e) {
                log.debug(e.getLocalizedMessage());
            }
            try {
                emptySudoku.solveGame();
            } catch (NoSolution noSolution) {
                noSolution.printStackTrace();
            }
            emptySudoku.attach(ob2);
            try {
                emptySudoku.set(0, 0, -6);
            } catch (Exception e) {
                log.debug(e.getLocalizedMessage());
            }
            assertEquals(emptySudoku.getAmountObservers(), 2);
            emptySudoku.detach(ob1);
            assertEquals(emptySudoku.getAmountObservers(), 1);
            emptySudoku.detach(ob2);
            assertEquals(emptySudoku.getAmountObservers(), 0);
            Locale.setDefault(new Locale("en"));
        }

    }

    @Test
    void toStringTest(){
        String expected = "SudokuBoard{board=[[SudokuField{value=0}, SudokuField{value=0}, " +
                "SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}], [SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}]," +
                " [SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}], [SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}], [SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}], [SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}]," +
                " [SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}], [SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}," +
                " SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, " +
                "SudokuField{value=0}, SudokuField{value=0}], [SudokuField{value=0}, SudokuField{value=0}, " +
                "SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}, " +
                "SudokuField{value=0}, SudokuField{value=0}, SudokuField{value=0}]]}";
        assertEquals(expected, emptySudoku.toString());

    }

    @Test
    void hashCodeTest() {
        assertEquals(emptySudoku.hashCode(),emptySudoku.hashCode());
        assertEquals(emptySudoku.hashCode(),emptySudoku2.hashCode());
        try {
            emptySudoku2.solveGame();
        } catch (NoSolution noSolution) {
            noSolution.printStackTrace();
        }
        assertNotEquals(emptySudoku.hashCode(),emptySudoku2.hashCode());
    }

    @Test
    void equalsTest() {
        assertTrue(emptySudoku.equals(emptySudoku));
        assertTrue(emptySudoku2.equals(emptySudoku));
        assertTrue(emptySudoku.equals(emptySudoku2));
        assertTrue(emptySudoku2.equals(emptySudoku2));
        try {
            emptySudoku2.solveGame();
        } catch (NoSolution noSolution) {
            noSolution.printStackTrace();
        }
        assertFalse(emptySudoku.equals(emptySudoku2));
        assertFalse(emptySudoku2.equals(emptySudoku));
    }

    @Test
    void cloneTest() throws CloneNotSupportedException, NoSolution {
        SudokuBoard cloneBoard;
        emptySudoku.solveGame();
        cloneBoard = emptySudoku.clone();
        for (int i = 0; i < 9;i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(cloneBoard.get(i,j),emptySudoku.get(i,j));
            }
        }
        System.out.println("Przed zmianą");
        System.out.println(cloneBoard.showSudoku());
        System.out.println(emptySudoku.showSudoku());
        try {
            cloneBoard.set(0,0,0);
        } catch (InvalidValueException e) {
            log.debug(e.getLocalizedMessage());
        }
        System.out.println("Przed zmianą");
        System.out.println(cloneBoard.showSudoku());
        System.out.println(emptySudoku.showSudoku());
    }


}