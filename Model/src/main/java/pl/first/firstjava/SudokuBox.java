package pl.first.firstjava;

import java.util.List;

public class SudokuBox extends SudokuObject {
    public SudokuBox(List<SudokuField> fields) throws InvalidSizeObjectException {
        super(fields);
    }
}
