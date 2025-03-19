package com.wenzejin;

import com.wenzejin.lox.Lox;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        }

        try {
            if (args.length == 0) {
                Lox.runPrompt();
            } else {
                Lox.runFile(args[0]);
            }
        } catch (IOException e) {
            System.err.println("IOException occurred when running Lox: " + e.getMessage());
        }
    }
}