package com.uchoa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Uchoash {

    public static void main(String[] args) {
        Uchoash shell = new Uchoash();
        shell.start();
    }

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;

        while (true) {
            try {
                System.out.print("> ");
                command = reader.readLine();
                if (command.equals("exit")) {
                    break;
                } else if (command.equals("help")) {
                    printHelp();
                } else {
                    executeCommand(command);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void printHelp() {
        System.out.println("Stephen Brennan's LSH");
        System.out.println("Type program names and arguments, and hit enter.");
        System.out.println("The following are built in:");
        System.out.println("  exit");
        System.out.println("  help");
        System.out.println("Use the man command for information on other programs.");
    }

    private void executeCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}