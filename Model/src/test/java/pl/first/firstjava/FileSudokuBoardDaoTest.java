package pl.first.firstjava;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.NotSerializableException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {
    private SudokuBoard fileBoard;
    private FileSudokuBoardDao file;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FileSudokuBoardDaoTest.class);

    @Test
    void readWrite() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        file=  factory.getFileDao("plik");
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        try {
            try {
                board.solveGame();
            } catch (NoSolution noSolution) {
                noSolution.printStackTrace();
            }
            try {
                file.write(board);
            } catch (IOException e) {
                throw new InvalidSave_LoadException("invalidSL", e);
            }
            try {
                fileBoard = file.read();
            } catch (ClassNotFoundException e) {
                throw new NotFoundClassException("lackClass", e);
            } catch (IOException e) {
                throw new InvalidSave_LoadException("invalidSL", e);
            }

        } catch (NotFoundClassException e) {
            log.debug(e.getLocalizedMessage()+e.getCause());
        } catch (InvalidSave_LoadException e) {
            log.debug(e.getLocalizedMessage()+e.getCause());
        }


        try {
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(fileBoard,board);
        try {
            file.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        InvalidSave_LoadException ex1 = new InvalidSave_LoadException("invalidSL", new Throwable("Coś nie tak"));
        assertTrue("Niepoprawna operacja na plikach zapisu: java.lang.Throwable: Coś nie tak".equals(ex1.getLocalizedMessage()+ex1.getCause()));
        NotFoundClassException ex2 = new NotFoundClassException("lackClass", new Throwable("Coś nie tak"));
        assertTrue("Zapisana plansza jest niepoprawna: java.lang.Throwable: Coś nie tak".equals(ex2.getLocalizedMessage()+ex1.getCause()));

    }

    @Test
    void readWrite2() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        JdbcSudokuBoardDao file =  factory.getJdbcDao("Sudoku");
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(solver1);
        SudokuSolver solver2 = new BacktrackingSudokuSolver();
        SudokuBoard board2 = new SudokuBoard(solver2);
        board.setName("Tab1test");
        board1.setName("Tab2test");
        board2.setName("Tab3test");
        try {
            board.solveGame();
            board1.solveGame();
        } catch (NoSolution noSolution) {
            System.out.println("Cos wie");
        }
        try {
            file.write(board);
            file.write(board1);
            file.write(board2);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        List<SudokuBoard> list = file.findAll();
        assertEquals(board.toString(), list.get(0).toString());
        assertEquals(file.findByName("Tab2test").toString(), board1.toString());
//        file.remove(list.get(2));
//        file.remove(list.get(1));
        SudokuBoard readboard =file.readBoard("Tab1test");
        assertEquals(board.toString(), readboard.toString());


    }
}