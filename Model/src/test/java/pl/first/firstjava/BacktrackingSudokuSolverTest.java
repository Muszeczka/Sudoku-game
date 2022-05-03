package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {

    @Test
    void solve() {
        for(int i=0;i<1000;i++){
            SudokuSolver solver = new BacktrackingSudokuSolver();
            SudokuSolver solver2 = new BacktrackingSudokuSolver();
            SudokuBoard sudoku= new SudokuBoard(solver);
            try {
                sudoku.solveGame();
            } catch (NoSolution noSolution) {
            }
            SudokuBoard sudoku2= new SudokuBoard(solver2);
            try {
                sudoku2.set(0,1,2);
            } catch (InvalidValueException e) {
            }
            try {
                sudoku2.solveGame();
            } catch (NoSolution noSolution) {
            }
            int[][] sudokutest= new int[9][9];
            int[][] testsudoku2= new int[9][9];
            int differences=0;
            for(int j=0;j<9;j++){
                for (int k=0;k<9;k++) {
                    sudokutest[j][k] = sudoku.get(j, k);
                    testsudoku2[j][k] = sudoku2.get(j, k);
                    for(int l=0;l<9;l++)
                    {
                        if(k!=l) {
                            assertTrue(sudokutest[j][l] !=sudokutest[j][k]);
                            assertTrue(testsudoku2[j][l] !=testsudoku2[j][k]);
                        }
                        if(j!=l) {
                            assertTrue(sudokutest[l][k] !=sudokutest[j][k]);
                            assertTrue(testsudoku2[l][k] !=testsudoku2[j][k]);
                        }
                    }
                    for(int l=j-(j%3);l<(j-(j%3))+3;l++){
                        for(int m=k-(k%3);m<(k-(k%3))+3;m++)
                        {
                            if(l!=j&&k!=m) {
                                assertTrue(sudokutest[l][m] != sudokutest[j][k]);
                                assertTrue(testsudoku2[l][m] != testsudoku2[j][k]);
                            }
                        }
                    }
                    if(sudokutest[j][k]!=testsudoku2[j][k])differences+=1;
                }
                assertTrue(differences!=0);
            }
        }
        SudokuSolver solver3 = new BacktrackingSudokuSolver();
        SudokuBoard sudoku= new SudokuBoard(solver3);
    }
}