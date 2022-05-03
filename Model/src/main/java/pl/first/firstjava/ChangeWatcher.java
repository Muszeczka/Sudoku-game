package pl.first.firstjava;

public class ChangeWatcher implements Observer {
    private final SudokuBoard sudokuBoard;
    private boolean isDisplayFailedChange;


    public ChangeWatcher(SudokuBoard board, boolean isDisplayFailedChange) {
        this.sudokuBoard = board;
        this.isDisplayFailedChange = isDisplayFailedChange;
    }

    @Override
    public void update(String text) {
        if (text.equals(" ") || (isDisplayFailedChange == true
                && text.equals(" NIE "))) {
            System.out.println("Wartość" + text + "została zmieniona :)");
        }
    }
}
