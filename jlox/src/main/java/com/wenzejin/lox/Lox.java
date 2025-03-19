package com.wenzejin.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Lox {
    public static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
    }

    public static void runPrompt() throws IOException {
        String greeting = "Welcome to Lox! Implemented by Wenze Jin. \n Version 0.1.0";
        System.out.println(greeting);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        // REPL
        while (true) {
            System.out.print("> ");
            String line = br.readLine();
            if (line == null) {
                break;
            }
            run(line);
        }
    }

    private static void run(String source) {

    }
}
