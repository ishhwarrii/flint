package com.ishhwarrii.flint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    @Test
    public void testSetAndGet_returnsStoredValue() {
        Database db = new Database();
        db.set("name", "Ishwari");
        assertEquals("Ishwari", db.get("name"));
    }

    @Test
    public void testGet_missingKey_returnsNull() {
        Database db = new Database();
        assertNull(db.get("doesNotExist"));
    }
}