package pl.first.firstjava;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.slf4j.LoggerFactory;


public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FileSudokuBoardDao.class);
    private String name;

    @Override
    public SudokuBoard read() throws ClassNotFoundException, IOException {
        try (FileInputStream fileInputStream = new FileInputStream(getName());
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return  (SudokuBoard) objectInputStream.readObject();
        }
    }

    @Override
    public void write(SudokuBoard obj) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(getName());
             ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream)) {
            objectInputStream.writeObject(obj);
        }

    }

    public FileSudokuBoardDao(String name) {
        this.name = name + ".ser";
    }

    @Override
    public void close() {
        log.debug("Correct closing.");
    }

    public String getName() {
        return name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
