package pl.first.firstjava;

public class SudokuBoardDaoFactory {

    public FileSudokuBoardDao getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public JdbcSudokuBoardDao getJdbcDao(String unitName) {
        return new JdbcSudokuBoardDao(unitName);
    }
}
