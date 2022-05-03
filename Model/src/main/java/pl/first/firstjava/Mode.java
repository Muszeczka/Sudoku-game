package pl.first.firstjava;

import java.util.Random;
import org.slf4j.LoggerFactory;

public class Mode {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Mode.class);

    public void choseLevel(String level, SudokuBoard userBoard) {

        Random rand = new Random();
        int randomNumber1;
        int randomNumber2;
        if (level.equals("easy")) {
            for (int i = 0; i < 10; i++) {
                randomNumber1 = (rand.nextInt(9));
                randomNumber2 = (rand.nextInt(9));
                try {
                    userBoard.set(randomNumber1,randomNumber2,0);
                } catch (InvalidValueException e) {
                    log.debug(e.getLocalizedMessage());
                }
            }
        } else if (level.equals("medium")) {
            for (int i = 0; i < 20; i++) {
                randomNumber1 = (rand.nextInt(9));
                randomNumber2 = (rand.nextInt(9));
                try {
                    userBoard.set(randomNumber1,randomNumber2,0);
                } catch (InvalidValueException e) {
                    log.debug(e.getLocalizedMessage());
                }
            }
        } else {
            for (int i = 0; i < 60; i++) {
                randomNumber1 = (rand.nextInt(9));
                randomNumber2 = (rand.nextInt(9));
                try {
                    userBoard.set(randomNumber1,randomNumber2,0);
                } catch (InvalidValueException e) {
                    log.debug(e.getLocalizedMessage());
                }
            }
        }
    }
}
