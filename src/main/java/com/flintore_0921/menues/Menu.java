package com.flintore_0921.menues;

import java.io.PrintStream;
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

    protected void println(Object value){
        this.PRINTER.println(value);
    }

    protected void print(Object value){
        this.PRINTER.print(value);
    }

    protected void  printLineSeparator(String separator){
        this.PRINTER.println(separator.repeat(COUNT_SEPARATOR_LENGTH));
    }

    protected void  printLineSeparator(){
        printLineSeparator(LINE_SEPARATOR);
    }

    protected boolean validate(int option, int maxValue) {
        return option > EXIT_VALUE && option < maxValue;
    }
}
