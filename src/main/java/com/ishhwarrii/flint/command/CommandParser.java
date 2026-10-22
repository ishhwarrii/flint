package com.ishhwarrii.flint.command;

import java.util.ArrayList;
import java.util.List;

public final class CommandParser {

    private CommandParser() {
    }

    public static List<String> parse(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        boolean hasToken = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (inQuotes) {
                if (c == '"') {
                    inQuotes = false;
                } else {
                    current.append(c);
                }
            } else if (c == '"') {
                inQuotes = true;
                hasToken = true;
            } else if (Character.isWhitespace(c)) {
                if (hasToken) {
                    tokens.add(current.toString());
                    current.setLength(0);
                    hasToken = false;
                }
            } else {
                current.append(c);
                hasToken = true;
            }
        }

        if (inQuotes) {
            throw new IllegalArgumentException("unbalanced quotes in request");
        }
        if (hasToken) {
            tokens.add(current.toString());
        }
        return tokens;
    }
}
