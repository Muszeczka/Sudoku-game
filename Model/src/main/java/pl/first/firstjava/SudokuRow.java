package pl.first.firstjava;

import java.util.List;

public class SudokuRow extends SudokuObject {
    public SudokuRow(List<SudokuField> fields) throws InvalidSizeObjectException {
        super(fields);
    }
}
