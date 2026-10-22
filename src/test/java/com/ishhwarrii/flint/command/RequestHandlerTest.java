package com.ishhwarrii.flint.command;

import com.ishhwarrii.flint.storage.Database;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestHandlerTest {

    private RequestHandler newHandler() {
        return new RequestHandler(new CommandDispatcher(new Database()));
    }

    @Test
    public void handle_setThenGet_worksEndToEnd() {
        RequestHandler handler = newHandler();

        assertEquals("OK", handler.handle("SET name Ishwari"));
        assertEquals("Ishwari", handler.handle("GET name"));
    }

    @Test
    public void handle_quotedValue_isStoredAsOneArgument() {
        RequestHandler handler = newHandler();

        assertEquals("OK", handler.handle("SET \"full name\" \"Ishwari Deshmukh\""));
        assertEquals("Ishwari Deshmukh", handler.handle("GET \"full name\""));
    }

    @Test
    public void handle_existsAndDel_reflectStoredState() {
        RequestHandler handler = newHandler();
        handler.handle("SET name Ishwari");

        assertEquals("1", handler.handle("EXISTS name"));
        assertEquals("1", handler.handle("DEL name"));
        assertEquals("0", handler.handle("EXISTS name"));
        assertEquals("0", handler.handle("DEL name"));
    }

    @Test
    public void handle_missingKey_returnsNull() {
        assertNull(newHandler().handle("GET doesNotExist"));
    }

    @Test
    public void handle_unbalancedQuote_returnsErrorInsteadOfThrowing() {
        assertEquals("ERR unbalanced quotes in request",
                newHandler().handle("SET name \"Ishwari"));
    }

    @Test
    public void handle_blankLine_returnsError() {
        assertEquals("ERR empty command", newHandler().handle("   "));
    }

    @Test
    public void handle_unknownCommand_returnsError() {
        assertEquals("ERR unknown command 'FLY'", newHandler().handle("FLY away"));
    }
}
