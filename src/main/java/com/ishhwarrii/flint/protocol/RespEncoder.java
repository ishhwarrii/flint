package com.ishhwarrii.flint.protocol;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public final class RespEncoder {

    private static final byte[] CRLF = {'\r', '\n'};

    private RespEncoder() {
    }

    public static byte[] encode(RespValue value) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        write(out, value);
        return out.toByteArray();
    }

    private static void write(ByteArrayOutputStream out, RespValue value) {
        switch (value) {
            case RespValue.SimpleString s -> line(out, "+" + s.value());
            case RespValue.ErrorReply e -> line(out, "-" + e.message());
            case RespValue.IntegerReply i -> line(out, ":" + i.value());
            case RespValue.NullBulk n -> line(out, "$-1");
            case RespValue.BulkString b -> {
                byte[] bytes = b.value().getBytes(StandardCharsets.UTF_8);
                line(out, "$" + bytes.length);
                out.writeBytes(bytes);
                out.writeBytes(CRLF);
            }
            case RespValue.ArrayReply a -> {
                line(out, "*" + a.items().size());
                for (RespValue item : a.items()) {
                    write(out, item);
                }
            }
        }
    }

    private static void line(ByteArrayOutputStream out, String text) {
        out.writeBytes(text.getBytes(StandardCharsets.UTF_8));
        out.writeBytes(CRLF);
    }
}
