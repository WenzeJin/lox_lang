package com.wenzejin.error;

public class LoxError {

    static boolean hadError = false;

    public static void error(int line, String message) {
        report(line, "", message);
        hadError = true;
    }

    public static void error(String source, int line, int column, String message) {
        report(source, line, column, "", message);
        hadError = true;
    }

    private static void report(int line, String prefix, String message) {
        System.err.println( "Error " + prefix + "at line " + line + " : " + message);
    }

    private static void report(String source, int line, int column, String prefix, String message) {
        System.err.println( "Error " + prefix + "at line " + line + " : " + message);
        System.err.println("\t" + line + "| " + source);
        System.err.println("\t" + " ".repeat(column + 2) + "^");
    }
}
