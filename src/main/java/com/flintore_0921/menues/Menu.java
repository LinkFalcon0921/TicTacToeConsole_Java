package com.flintore_0921.menues;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Scanner;

import static com.flintore_0921.componentes.Constants.Menu.*;

abstract class Menu {
    protected final PrintStream PRINTER;
    protected final Scanner INPUT_RESPONSE;

    public Menu() {
        this(System.out, new Scanner(System.in));
    }

    public Menu(PrintStream PRINTER, Scanner responseReceiver) {
        this.PRINTER = PRINTER;
        this.INPUT_RESPONSE = responseReceiver;
    }

    abstract void printMenu();

    protected void println(Object xyz) {
        this.PRINTER.println(xyz);
    }

    protected void print(Object xyz) {
        this.PRINTER.print(xyz);
    }

    protected void printMessageWithSeparators(String xyz) {
        printLineSeparator();
        println(xyz);
        printLineSeparator();
    }

    protected void printLineSeparator(String separator) {
        if (!separator.isBlank()) {
            separator = separator.repeat(COUNT_SEPARATOR_LENGTH);
        }

        this.PRINTER.println(separator);
    }

    protected void printLineSeparator() {
        printLineSeparator(LINE_SEPARATOR);
    }

    protected void fullLineSeparatorSpace() {
        printLineSeparator();
        printLineSeparator("");
    }

    /*label: While the value is not the Exit value or not surpass than the maxValue*/
    protected boolean validate(int option, int maxValue) {
        return option > EXIT_VALUE && option <= maxValue;
    }

    protected <R, T extends Collection<R>> void printListCollection(T availableIcons) {
        int count = 0;
        for (Object obj : availableIcons) {
            println(String.format("%d. %s", ++count, obj));
        }
    }

    protected boolean isExit(int option) {
        return option == EXIT_VALUE;
    }

    protected boolean isExit(String option) {
        return Integer.parseInt(option) == EXIT_VALUE;
    }
}
