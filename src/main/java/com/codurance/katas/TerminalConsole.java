package com.codurance.katas;

import java.util.Scanner;

public class TerminalConsole implements Console {

    private final Scanner scanner;

    public TerminalConsole(){

        scanner = new Scanner(System.in);
    }
    @Override
    public void writeLineToOutput(String line) {
        System.out.println(line);

    }

    @Override
    public String readLineFromInput() {
        return scanner.nextLine();
    }
}
