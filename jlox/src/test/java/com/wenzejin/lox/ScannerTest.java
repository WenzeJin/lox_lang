package com.wenzejin.lox;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScannerTest {

    @Test
    void testGetAllTokens() {
        String source = "( ) { } , . ;";
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.getAllTokens();
        List<Token> expectedTokens = new ArrayList<>(List.of(
                new Token(TokenType.LEFT_PAREN, "(", null, 1, 1),
                new Token(TokenType.RIGHT_PAREN, ")", null, 1, 3),
                new Token(TokenType.LEFT_BRACE, "{", null, 1, 5),
                new Token(TokenType.RIGHT_BRACE, "}", null, 1, 7),
                new Token(TokenType.COMMA, ",", null, 1, 9),
                new Token(TokenType.DOT, ".", null, 1, 11),
                new Token(TokenType.SEMICOLON, ";", null, 1, 13)
        ));
        assertEquals(expectedTokens, tokens);

        source = "- + / * ! != = == > >= < <=";
        scanner = new Scanner(source);
        tokens = scanner.getAllTokens();
        expectedTokens = new ArrayList<>(List.of(
                new Token(TokenType.MINUS, "-", null, 1, 1),
                new Token(TokenType.PLUS, "+", null, 1, 3),
                new Token(TokenType.SLASH, "/", null, 1, 5),
                new Token(TokenType.STAR, "*", null, 1, 7),
                new Token(TokenType.BANG, "!", null, 1, 9),
                new Token(TokenType.BANG_EQUAL, "!=", null, 1, 11),
                new Token(TokenType.EQUAL, "=", null, 1, 14),
                new Token(TokenType.EQUAL_EQUAL, "==", null, 1, 16),
                new Token(TokenType.GREATER, ">", null, 1, 19),
                new Token(TokenType.GREATER_EQUAL, ">=", null, 1, 21),
                new Token(TokenType.LESS, "<", null, 1, 24),
                new Token(TokenType.LESS_EQUAL, "<=", null, 1, 26)
        ));
        assertEquals(expectedTokens, tokens);

        source = "//this is a comment\n + - * /";
        scanner = new Scanner(source);
        tokens = scanner.getAllTokens();
        expectedTokens = new ArrayList<>(List.of(
                new Token(TokenType.PLUS, "+", null, 2, 2),
                new Token(TokenType.MINUS, "-", null, 2, 4),
                new Token(TokenType.STAR, "*", null, 2, 6),
                new Token(TokenType.SLASH, "/", null, 2, 8)
        ));
        assertEquals(expectedTokens, tokens);

        source = "= \"hello\" +++";
        scanner = new Scanner(source);
        tokens = scanner.getAllTokens();
        expectedTokens = new ArrayList<>(List.of(
                new Token(TokenType.EQUAL, "=", null, 1, 1),
                new Token(TokenType.STRING, "\"hello\"", "hello", 1, 3),
                new Token(TokenType.PLUS, "+", null, 1, 11),
                new Token(TokenType.PLUS, "+", null, 1, 12),
                new Token(TokenType.PLUS, "+", null, 1, 13)
        ));
    }
}