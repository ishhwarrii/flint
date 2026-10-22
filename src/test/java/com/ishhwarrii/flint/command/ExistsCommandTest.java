package com.ishhwarrii.flint.command;

import com.ishhwarrii.flint.storage.Database;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExistsCommandTest {

    private final ExistsCommand command = new ExistsCommand();

    @Test
    public void execute_existingKey_returnsOne() {
        Database db = new Database();
        db.set("name", "Ishwari");

        assertEquals("1", command.execute(db, List.of("name")));
    }

    @Test
    public void execute_missingKey_returnsZero() {
        Database db = new Database();

        assertEquals("0", command.execute(db, List.of("doesNotExist")));
    }

    @Test
    public void execute_tooManyArgs_returnsError() {
        Database db = new Database();

        assertEquals("ERR wrong number of arguments for 'EXISTS' command",
                command.execute(db, List.of("a", "b")));
    }
}
