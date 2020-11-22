package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 10;
    
    private static final String HOME = System.getProperty("user.home");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final File DEFAULT_FILE = new File(HOME + SEPARATOR + "output.txt");
    
    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * 
     */
    public DrawNumberApp() {
        this.model = new DrawNumberImpl(MIN, MAX, ATTEMPTS);
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    public void saveGameResults() throws FileNotFoundException {
        try (PrintStream ps = new PrintStream(DEFAULT_FILE)){
            ps.println(this.model.);
        }
    }
    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
