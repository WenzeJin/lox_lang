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

    public void reset() {
        start = 0;
        current = 0;
        line = 1;
        column = 1;
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
            case '!':
                token = genToken(match('=') ? BANG_EQUAL : BANG, null);
                break;
            case '=':
                token = genToken(match('=') ? EQUAL_EQUAL : EQUAL, null);
                break;
            case '<':
                token = genToken(match('=') ? LESS_EQUAL : LESS, null);
                break;
            case '>':
                token = genToken(match('=') ? GREATER_EQUAL : GREATER, null);
                break;
            case '/':
                if (match('/')) {
                    while (peek() != '\n' && !isAtEnd()) advance();
                } else {
                    token = genToken(SLASH, null);
                }
                break;
            case '"':
                token = string();
                break;
            case ' ':
            case '\r':
            case '\t':
                column++;
                break;
            case '\n':
                line++;
                column = 1;     // column is incremented at the end of the method
                break;
            default:
                LoxError.error(getSourceLine(), line, column - 1, "Unexpected character: " + c);
        }
        return token;
    }

    private char advance() {

        return source.charAt(current++);
    }

    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;

        current++;
        return true;
    }

    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private Token string() {
        int oldColumn = column;
        int oldLine = line;
        int realColumn = column;
        while (peek() != '"' && !isAtEnd()) {

            if (peek() == '\n') {
                line++;
                realColumn = 0;
            }
            advance();
            realColumn++;
        }

        if (isAtEnd()) {
            LoxError.error(line, "Unterminated string.");
            return null;
        }

        // The closing ".
        advance();
        realColumn++;
        // Trim the surrounding quotes.
        String value = source.substring(start + 1, current - 1);
        Token token = genToken(STRING, value, oldLine, oldColumn);
        column = realColumn;
        return token;
    }

    private Token genToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        Token token = new Token(type, text, literal, line, column);
        column += text.length();
        return token;
    }

    private Token genToken(TokenType type, Object literal, int line, int column) {
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
