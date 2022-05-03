package pl.first.firstjava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.Serializable;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Table
public class SudokuBoard implements Observable, Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<SudokuField> board;

    private String name;
    @Transient
    private  SudokuSolver solver;
    @Transient
    private Set<Observer> observers = new HashSet<>();
    @Transient
    private boolean stopObservers = false;
    private static final Logger log = LoggerFactory.getLogger(SudokuBoard.class);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SudokuBoard(SudokuSolver newSolver) {
        this.board = Arrays.asList(new SudokuField[81]);
        for (int i = 0; i < 81; i++) {

                this.board.set(i, new SudokuField());
                board.get(i).setSudokuBoard(this);
        }
        this.solver = newSolver;

    }


    public SudokuBoard(List<SudokuField> newBoard, SudokuSolver newSolver) {
        this.board = newBoard;
        this.solver = newSolver;
    }

    public SudokuBoard() {

    }

    public int get(int x, int y) {
        return board.get(x*9+y).getFieldValue();
    }

    public void solveGame() throws NoSolution {
        stopObservers = true;
        solver.solve(this);
        stopObservers = false;
        if (!checkBoard()) {
            throw new NoSolution();
        }
    }

    public void set(int x, int y, int value) throws InvalidValueException {

        try {
            board.get(x*9+y).setValue(value);
        } catch (InvalidValueFieldException e) {
            throw new InvalidValueException(e.getLocalizedMessage());
        }
        if (stopObservers == false) {
            if (value == board.get(x*9+y).getFieldValue()) {
                notifyObservers(" ");
            } else {
                notifyObservers(" NIE ");
            }
        }
    }

    public SudokuRow getRow(int y) throws InvalidValueException {
        List<SudokuField> tab = Arrays.asList(new SudokuField[9]);
        SudokuRow row = null;
        try {
        for (int i = 0; i < 9; i++) {
            tab.set(i,new SudokuField());
            tab.get(i).setValue(board.get(y*9+i).getFieldValue());
        }
        row = new SudokuRow(tab);

        } catch (Exception e) {
            throw new InvalidValueException(e.getLocalizedMessage());
        }
        return row;
    }

    public SudokuBox getBox(int x, int y) throws InvalidValueException {
        List<SudokuField> tab = Arrays.asList(new SudokuField[9]);
        SudokuBox box = null;;
        int tabIterator = 0;
        try {
            for (int i = x - (x % 3);
                 i < (x - (x % 3)) + 3; i++) {
                for (int j = y - (y % 3);
                     j < (y - (y % 3)) + 3; j++, tabIterator++) {
                    tab.set(tabIterator, new SudokuField());
                    tab.get(tabIterator).setValue(board.get(i*9+j).getFieldValue());
                }
            }
            box = new SudokuBox(tab);

        } catch (Exception e) {
            throw new InvalidValueException(e.getLocalizedMessage());
        }
        return box;
    }

    public SudokuColumn getColumn(int x) throws InvalidValueException {
        List<SudokuField> tab = Arrays.asList(new SudokuField[9]);
        SudokuColumn column = null;
        try {
            for (int i = 0; i < 9; i++) {
                tab.set(i,new SudokuField());
                tab.get(i).setValue(board.get(i*9+x).getFieldValue());
            }
            column = new SudokuColumn(tab);
        } catch (Exception e) {
            throw new InvalidValueException(e.getLocalizedMessage());
        }
        return column;
    }

    public String showSudoku() {
        String tmpStream = "";
        for (int i = 0;i < 9;i++) {
            for (int j = 0;j < 9;j++) {
                tmpStream += board.get(i*9+j).getFieldValue();
                tmpStream += " ";

            }
            tmpStream += ",";
        }
        return tmpStream;
    }

    private boolean checkBoard() {
        try {
            for (int i = 0; i < 9; i++) {
                if (!getRow(i).verify() || !getColumn(i).verify()) {
                    return false;
                }
                for (int j = 0; j < 9; j = j + 3) {
                    if (!getBox(i, j).verify()) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage());
        }
        return true;
    }


    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String  text) {
        for (Observer i: observers) {
            i.update(text);
        }
    }


    public int getAmountObservers() {
        return observers.size();
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", name)
                .add("board", board).add("id", id)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SudokuBoard)) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;
        return Objects.equal(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(board);
    }

    @Override
    public final SudokuBoard clone() throws CloneNotSupportedException {
        SudokuBoard cloneBoard = (SudokuBoard) super.clone();
        List<SudokuField> cloneList = Arrays.asList(new SudokuField[81]);
        for (int i = 0; i < 81; i++) {

                cloneList.set(i, (SudokuField) this.board.get(i).clone());

        }
        cloneBoard.board = cloneList;
        return cloneBoard;
    }

}
