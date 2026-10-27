package com.ishhwarrii.flint.protocol;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RespEncoderTest {

    private static String encode(RespValue value) {
        return new String(RespEncoder.encode(value), StandardCharsets.UTF_8);
    }

    @Test
    public void simpleString_isPlusPrefixedLine() {
        assertEquals("+OK\r\n", encode(new RespValue.SimpleString("OK")));
    }

    @Test
    public void simpleString_withLineBreak_isRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new RespValue.SimpleString("a\r\nb"));
    }

    @Test
    public void error_isMinusPrefixedLine() {
        assertEquals("-ERR bad\r\n", encode(new RespValue.ErrorReply("ERR bad")));
    }

    @Test
    public void error_lineBreaksAreReplacedSoClientsCannotInjectReplies() {
        assertEquals("-ERR a  b\r\n", encode(new RespValue.ErrorReply("ERR a\r\nb")));
    }

    @Test
    public void integer_positive() {
        assertEquals(":42\r\n", encode(new RespValue.IntegerReply(42)));
    }

    @Test
    public void integer_negative() {
        assertEquals(":-1\r\n", encode(new RespValue.IntegerReply(-1)));
    }

    @Test
    public void bulkString_carriesItsLength() {
        assertEquals("$7\r\nIshwari\r\n", encode(new RespValue.BulkString("Ishwari")));
    }

    @Test
    public void bulkString_empty() {
        assertEquals("$0\r\n\r\n", encode(new RespValue.BulkString("")));
    }

    @Test
    public void bulkString_lengthCountsBytesNotCharacters() {
        // "\u00e9" is one character but two bytes in UTF-8 (0xC3 0xA9)
        byte[] expected = {'$', '2', '\r', '\n', (byte) 0xC3, (byte) 0xA9, '\r', '\n'};

        assertArrayEquals(expected, RespEncoder.encode(new RespValue.BulkString("\u00e9")));
    }

    @Test
    public void nullBulk_isMinusOneLength() {
        assertEquals("$-1\r\n", encode(new RespValue.NullBulk()));
    }

    @Test
    public void array_ofBulkStrings_matchesTheShapeClientsSend() {
        RespValue command = new RespValue.ArrayReply(List.of(
                new RespValue.BulkString("SET"),
                new RespValue.BulkString("name")));

        assertEquals("*2\r\n$3\r\nSET\r\n$4\r\nname\r\n", encode(command));
    }

    @Test
    public void array_empty() {
        assertEquals("*0\r\n", encode(new RespValue.ArrayReply(List.of())));
    }
}
