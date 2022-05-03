package pl.first.firstjava;

import java.util.List;

public class SudokuColumn extends SudokuObject {
    public SudokuColumn(List<SudokuField> fields) throws InvalidSizeObjectException {
        super(fields);
    }
}
