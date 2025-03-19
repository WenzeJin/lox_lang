package com.wenzejin.lox;

import java.util.Objects;

public class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;
    final int column;

    public Token(TokenType type, String lexeme, Object literal, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return "<" + type + " " + lexeme + " " + literal + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        return type == token.type
                && Objects.equals(lexeme, token.lexeme)
                && Objects.equals(literal, token.literal)
                && line == token.line
                && column == token.column;
    }
}
