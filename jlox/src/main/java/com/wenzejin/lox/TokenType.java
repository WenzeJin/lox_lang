package com.wenzejin.lox;

/**
 * The TokenType enum represents the different types of tokens
 * that can be encountered in the Lox programming language.
 */
public enum TokenType {
    // Single-character tokens.
    LEFT_PAREN,  // (
    RIGHT_PAREN, // )
    LEFT_BRACE,  // {
    RIGHT_BRACE, // }
    COMMA,       // ,
    DOT,         // .
    MINUS,       // -
    PLUS,        // +
    SEMICOLON,   // ;
    SLASH,       // /
    STAR,        // *

    // One or two character tokens.
    BANG,        // !
    BANG_EQUAL,  // !=
    EQUAL,       // =
    EQUAL_EQUAL, // ==
    GREATER,     // >
    GREATER_EQUAL, // >=
    LESS,        // <
    LESS_EQUAL,  // <=

    // Literals.
    IDENTIFIER,  // Variable names, function names, etc.
    STRING,      // String literals
    NUMBER,      // Numeric literals

    // Keywords.
    AND,         // and
    CLASS,       // class
    ELSE,        // else
    FALSE,       // false
    FUN,         // fun
    FOR,         // for
    IF,          // if
    NIL,         // nil
    OR,          // or
    PRINT,       // print
    RETURN,      // return
    SUPER,       // super
    THIS,        // this
    TRUE,        // true
    VAR,         // var
    WHILE,       // while

    EOF          // End of file
}
