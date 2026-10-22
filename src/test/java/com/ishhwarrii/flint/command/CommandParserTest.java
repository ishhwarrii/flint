package com.ishhwarrii.flint.command;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {

    @Test
    public void parse_simpleLine_splitsOnWhitespace() {
        assertEquals(List.of("SET", "name", "Ishwari"),
                CommandParser.parse("SET name Ishwari"));
    }

    @Test
    public void parse_extraWhitespace_isIgnored() {
        assertEquals(List.of("GET", "name"),
                CommandParser.parse("  GET    name  "));
    }

    @Test
    public void parse_quotedValue_keepsInnerSpaces() {
        assertEquals(List.of("SET", "name", "Ishwari Deshmukh"),
                CommandParser.parse("SET name \"Ishwari Deshmukh\""));
    }

    @Test
    public void parse_emptyQuotes_producesEmptyToken() {
        assertEquals(List.of("SET", "name", ""),
                CommandParser.parse("SET name \"\""));
    }

    @Test
    public void parse_blankLine_returnsEmptyList() {
        assertTrue(CommandParser.parse("   ").isEmpty());
    }

    @Test
    public void parse_unbalancedQuote_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> CommandParser.parse("SET name \"Ishwari"));
    }
}
