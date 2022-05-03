package pl.first.firstjava;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.slf4j.LoggerFactory;


public class BacktrackingSudokuSolver implements SudokuSolver, Serializable {
    private static final org.slf4j.Logger log =
            LoggerFactory.getLogger(BacktrackingSudokuSolver.class);

    @Override
    public void solve(SudokuBoard board) {
        int[] emptyField = new int[]{-1, -1};
        /*
        if (checkEmptySudoku(board)) {
            Random rand = new Random();
            int randomNumber;
            int[] location = {0,0};
            for (int i = 0;i < 9;i++) {
                location[1] = i;
                do {
                    randomNumber = (rand.nextInt(9)) + 1;
                } while (!checkConflictNumber(location, randomNumber, board));
                board.set(0, i, randomNumber);

            }
        }
        if (findEmptyField(emptyField, board)) {
            for (int i = 1; i < 10; i++) {
                if (checkConflictNumber(emptyField, i, board)) {
                    board.set(emptyField[0], emptyField[1], i);
                    solve(board);
                    int[] checkcontinue = {-1,-1};
                    findEmptyField(checkcontinue, board);
                    if (checkcontinue[0] != -1) {
                        board.set(emptyField[0], emptyField[1], 0);
                    }
                }
            }
        }
         */

        Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        List<Integer> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        if (findEmptyField(emptyField, board)) {
            for (int i : intList) {
                if (checkConflictNumber(emptyField, i, board)) {
                    try {
                        board.set(emptyField[0], emptyField[1], i);
                    } catch (InvalidValueException e) {
                        log.debug(e.getLocalizedMessage());
                    }
                    solve(board);
                    int[] checkcontinue = {-1,-1};
                    findEmptyField(checkcontinue, board);
                    if (checkcontinue[0] != -1) {
                        try {
                            board.set(emptyField[0], emptyField[1], 0);
                        } catch (InvalidValueException e) {
                            log.debug(e.getLocalizedMessage());
                        }
                    }
                }
            }
        }
    }


    private boolean checkConflictNumber(int[] location, int number, SudokuBoard board) {
        for (int i = 0;i < 9;i++) {
            if (board.get(location[0], i) == number
                    || board.get(i, location[1]) == number) {
                return false;
            }
        }
        for (int i = location[0] - (location[0] % 3);
             i < (location[0] - (location[0] % 3)) + 3;i++) {
            for (int j = location[1] - (location[1] % 3);
                 j < (location[1] - (location[1] % 3)) + 3;j++) {
                if (board.get(i, j) == number) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
    public boolean checkEmptySudoku(SudokuBoard board) {
        if (board.get(0, 0) != 0) {
            return false;
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) != 0) {
                    return false;
                }
            }
        }
        return true;
    }
     */

    public boolean findEmptyField(int[] location, SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) == 0) {
                    location[0] = i;
                    location[1] = j;
                    return true;
                }

            }
        }
        return false;
    }
}
