package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardDaoFactoryTest {

    @Test
    void getFileDao() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        FileSudokuBoardDao boardDao;
        boardDao= (FileSudokuBoardDao) factory.getFileDao("plik");
        assertNotNull(boardDao);
        assertEquals(boardDao.getName(),  "plik.ser");
    }
}