package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private final List<String> history;
    private String nextStringToPrint;
    private String currentString;

    public ControllerImpl() {
        history = new ArrayList<>();
    }

    public final void setNextStringToPrint(final String s) {
        this.currentString = s;
        history.add(s);
        if (this.nextStringToPrint != null) {
            System.out.println(nextStringToPrint);
        } else {
            throw new IllegalArgumentException();
        }
        this.nextStringToPrint = s;
    }

    public final String getNextStringToPrint() {
        return nextStringToPrint;
    }

    public final List<String> getHistory() {
       return this.history;
    }

    public final void printCurrentString() {
        if (this.currentString == null) {
            throw new IllegalStateException();
        }
        System.out.println(this.currentString);
    }

}
