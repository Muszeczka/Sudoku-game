package pl.first.firstjava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Access(AccessType.FIELD)
public class SudokuField implements Serializable, Cloneable, Comparable {
    @Basic
    @Column(name = "value", nullable = false)
    private int value;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private SudokuBoard sudokuBoard;

    public void setSudokuBoard(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    public int getFieldValue() {
        return value;
    }

    public void setValue(int value) throws InvalidValueFieldException {
        if (value < 10 && value >= 0) {
            this.value = value;
        } else {
            throw new InvalidValueFieldException("InvalidValueField");
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SudokuField)) {
            return false;
        }
        SudokuField that = (SudokuField) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public final SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }

    @Override
    public int compareTo(Object o) {
        return this.getFieldValue() - ((SudokuField)o).getFieldValue();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
