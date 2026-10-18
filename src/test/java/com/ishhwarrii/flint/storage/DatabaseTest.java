package com.ishhwarrii.flint.storage;

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

    @Test
    public void testDelete_existingKey_removesItAndReturnsTrue() {
        Database db = new Database();
        db.set("name", "Ishwari");

        boolean result = db.delete("name");

        assertTrue(result);
        assertNull(db.get("name"));
    }

    @Test
    public void testDelete_missingKey_returnsFalse() {
        Database db = new Database();
        boolean result = db.delete("doesNotExist");
        assertFalse(result);
    }
}
