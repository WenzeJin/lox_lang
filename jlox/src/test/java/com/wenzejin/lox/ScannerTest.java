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
    }
}