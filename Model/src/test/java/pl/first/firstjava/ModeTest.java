package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModeTest {

    @Test
    void choseLevelEasy() throws NoSolution {
        Mode mode = new Mode();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        int hashcode = board.hashCode();
        mode.choseLevel("easy", board);
        int hashcode2 = board.hashCode();
        assertNotEquals(hashcode, hashcode2);
    }

    @Test
    void choseLevelMedium() throws NoSolution {
        Mode mode = new Mode();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        int hashcode = board.hashCode();
        mode.choseLevel("medium", board);
        int hashcode2 = board.hashCode();
        assertNotEquals(hashcode, hashcode2);
    }

    @Test
    void choseLevelHard() throws NoSolution {
        Mode mode = new Mode();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        int hashcode = board.hashCode();
        mode.choseLevel("hard", board);
        int hashcode2 = board.hashCode();
        assertNotEquals(hashcode, hashcode2);
    }
}