package pl.cp.view;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.slf4j.LoggerFactory;
import pl.first.firstjava.*;


public class GameController {

    @FXML
    private MainController main;

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    @FXML
    private ResourceBundle bundle;

    @FXML
    private GridPane sudokuGrid;
    @FXML
    private Label dialogWindow;
    @FXML
    private ComboBox saves;

    List<String> listNS;

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(GameController.class);




    private SudokuBoard userBoard;
    private String level;
    private SudokuBoard cloneSudoku;
    private TextField[] sudokuTextField = new TextField[81];

    public GameController(String level) {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        userBoard = new SudokuBoard(solver);
        try {
            userBoard.solveGame();
        } catch (NoSolution noSolution) {
            noSolution.printStackTrace();
        }
        this.level = level;
        update_saves();
    }

    public void update_saves() {
//        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
//        JdbcSudokuBoardDao file =  factory.getJdbcDao("Sudoku");
//        List<SudokuBoard> list = file.findAll();
//        for ( int i =0; i< list.size(); i++) {
//            listNS.add(list.get(i).getName());
//        }
//        saves.setItems((ObservableList) listNS);
    }


    public GameController() {
    }

    public void levelShowSudoku() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        userBoard = new SudokuBoard(solver);
        try {
            userBoard.solveGame();
        } catch (NoSolution noSolution) {
            noSolution.printStackTrace();
        }
        log.info(bundle.getString("logNewSudoku"),userBoard.toString());
        Mode mode = new Mode();
        mode.choseLevel(level,userBoard);
        try {
            cloneSudoku = userBoard.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        updateGrid();

    }

    public void updateGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField text = new TextField();
                text.setPrefWidth(500);
                text.setPrefHeight(500);
                text.setText(String.valueOf(userBoard.get(i,j)));
                if (cloneSudoku.get(i,j) != 0) {
                    text.setEditable(false);
                } else {
                    text.setBackground(new Background(
                            new BackgroundFill(Color.rgb(173,210,131), null, null)));
                    text.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            try {
                                int value = Integer.parseInt(text.getText());
                                    clearDialogWindow();
                                    userBoard.set(sudokuGrid.getRowIndex(text),
                                            sudokuGrid.getColumnIndex(text),
                                            Integer.parseInt(text.getText()));
                                    log.info(bundle.getString("logSetFild"),
                                            sudokuGrid.getColumnIndex(text),
                                            sudokuGrid.getRowIndex(text),
                                            Integer.parseInt(text.getText()));
                            } catch (Exception  e) {
                                dialogWindow.setText(bundle.getString("invalidValue"));
                                log.info(bundle.getString("logInvalidValue"),text.getText());
                                text.setText("0");
                            }
                        }
                    });
                }
                sudokuTextField[(i * 9) + j] = text;
                sudokuGrid.add(sudokuTextField[(i * 9) + j], j,i);
            }
        }
    }


    @FXML
    public void exit() {
        main.loadMenuWindow();

    }


    public void clearDialogWindow() {
        dialogWindow.setText("");
    }

    @FXML
    public void checkSudoku() {
        boolean flaga = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (userBoard.get(i, j) == 0) {
                    flaga = false;
                }
            }
        }
        if (flaga) {
            flaga = true;
            try {
                userBoard.solveGame();
            } catch (NoSolution noSolution) {
                dialogWindow.setText(bundle.getString("defeat"));
                log.info(bundle.getString("logDefeat"));
                flaga = false;
            }
            if (flaga) {
                dialogWindow.setText(bundle.getString("win"));
                log.info(bundle.getString("logWin"));
            }
        } else {
            dialogWindow.setText(bundle.getString("noFilling"));
            log.warn(bundle.getString("logNotEnd"));
        }
    }

    public void setMain(MainController main) {
        this.main = main;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @FXML
    public void changeSudoku() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        userBoard = new SudokuBoard(solver);
        try {
            userBoard.solveGame();
        } catch (NoSolution noSolution) {
            noSolution.printStackTrace();
        }
        clearDialogWindow();
        levelShowSudoku();
    }

    @FXML
    public void save() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        FileSudokuBoardDao boardDao;
        JdbcSudokuBoardDao file =  factory.getJdbcDao("Sudoku");
        boardDao =  factory.getFileDao("plik");
        userBoard.setName("UserTable");
        try {
            boardDao.write(userBoard);
            FileSudokuBoardDao boardDao2;
            boardDao2 =  factory.getFileDao("plik2");
            boardDao2.write(cloneSudoku);
            file.write(userBoard);
            log.debug(bundle.getString("logSuccessfulSave"));
        } catch (IOException e) {
            log.error(bundle.getString("logAbortiveSave"),e);
        }
        try {
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void load() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        FileSudokuBoardDao boardDao;
        boardDao =  factory.getFileDao("plik");
        FileSudokuBoardDao boardDao2;
        boardDao2 =  factory.getFileDao("plik2");
        try {
            userBoard = boardDao.read();
            cloneSudoku = boardDao2.read();
            log.debug(bundle.getString("logSuccessfulLoad"));
        } catch (ClassNotFoundException e) {
            log.error(bundle.getString("logAbortiveLoad"),e);
        } catch (IOException e) {
            log.error(bundle.getString("logAbortiveLoad"),e);
        }
        updateGrid();

    }

    @FXML
    public  void chose_save() {

    }
}
