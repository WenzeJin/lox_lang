package com.wenzejin.lox;

import com.wenzejin.error.LoxError;

import java.util.ArrayList;
import java.util.List;

import static com.wenzejin.lox.TokenType.*;

public class Scanner {
    private final String source;

    // position of the current character in the source code
    private int start = 0;
    private int current = 0;
    private int line = 1;
    private int column = 1;


    public Scanner(String source) {
        this.source = source;
    }

    public List<Token> getAllTokens() {
        List<Token> tokens = new ArrayList<>();
        while (!isAtEnd()) {
            start = current;
            Token token = scanToken();
            if (token != null) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    public Token nextToken() {
        start = current;
        if (isAtEnd()) {
            return null;
        } else {
            return scanToken();
        }
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private Token scanToken() {
        char c = advance();
        Token token = null;
        switch (c) {
            case '(':
                token = genToken(LEFT_PAREN, null);
                break;
            case ')':
                token = genToken(RIGHT_PAREN, null);
                break;
            case '{':
                token = genToken(LEFT_BRACE, null);
                break;
            case '}':
                token = genToken(RIGHT_BRACE, null);
                break;
            case ',':
                token = genToken(COMMA, null);
                break;
            case '.':
                token = genToken(DOT, null);
                break;
            case '-':
                token = genToken(MINUS, null);
                break;
            case '+':
                token = genToken(PLUS, null);
                break;
            case ';':
                token = genToken(SEMICOLON, null);
                break;
            case '*':
                token = genToken(STAR, null);
                break;
            case ' ':
            case '\r':
            case '\t':
                break;
            case '\n':
                line++;
                column = 1;
                break;
            default:
                LoxError.error(getSourceLine(), line, column, "Unexpected character: " + c);
        }
        column++;
        return token;
    }

    private char advance() {

        return source.charAt(current++);
    }

    private char peek() {
        if (isAtEnd()) {
            return '\0';
        } else {
            return source.charAt(current);
        }
    }

    private Token genToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        return new Token(type, text, literal, line, column);
    }

    private String getSourceLine() {
        int lineStart = current - column + 1;
        while (lineStart > 0 && source.charAt(lineStart) != '\n') {
            lineStart--;
        }
        int lineEnd = current;
        while (lineEnd < source.length() && source.charAt(lineEnd) != '\n') {
            lineEnd++;
        }
        return source.substring(lineStart, lineEnd);
    }


}
