package com.uchoa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaShell {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;

        while (true) {
            System.out.print("> ");
            command = reader.readLine();
            if (command.equals("exit")) {
                break;
            } else {
                executeCommand(command);
            }
        }
    }

    private static void executeCommand(String command) {
        String os = System.getProperty("os.name").toLowerCase();
        String[] cmd;

        if (os.contains("win")) {
            cmd = new String[]{"cmd.exe", "/c", command};
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            cmd = new String[]{"/bin/bash", "-c", command};
        } else {
            System.out.println("Sorry, but your OS is not support.");
            return;
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
