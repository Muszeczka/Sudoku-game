package pl.first.firstjava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.Serializable;
import java.util.List;

public abstract class SudokuObject implements Cloneable, Serializable {
    private List<SudokuField> fields;

    protected SudokuObject(List<SudokuField> fields) throws InvalidSizeObjectException {
        if (fields.size() == 9) {
            this.fields = fields;
        } else {
            throw new InvalidSizeObjectException("InvalidSize");
        }
    }

    public  boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (fields.get(i).getFieldValue() == fields.get(j).getFieldValue() && i != j) {
                    return false;
                }
            }
        }
        return true;
    }

    public int get(int i) {
        return fields.get(i).getFieldValue();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("fields", fields)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SudokuObject)) {
            return false;
        }
        SudokuObject that = (SudokuObject) o;
        return Objects.equal(fields, that.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fields);
    }

    @Override
    public final Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
