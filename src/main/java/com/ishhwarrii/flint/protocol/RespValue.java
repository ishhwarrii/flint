package com.ishhwarrii.flint.protocol;

import java.util.List;
import java.util.Objects;

public sealed interface RespValue {

    record SimpleString(String value) implements RespValue {
        public SimpleString {
            Objects.requireNonNull(value);
            if (value.indexOf('\r') >= 0 || value.indexOf('\n') >= 0) {
                throw new IllegalArgumentException("simple strings cannot contain line breaks");
            }
        }
    }

    record ErrorReply(String message) implements RespValue {
        public ErrorReply {
            Objects.requireNonNull(message);
            message = message.replace('\r', ' ').replace('\n', ' ');
        }
    }

    record IntegerReply(long value) implements RespValue {
    }

    record BulkString(String value) implements RespValue {
        public BulkString {
            Objects.requireNonNull(value);
        }
    }

    record NullBulk() implements RespValue {
    }

    record ArrayReply(List<RespValue> items) implements RespValue {
        public ArrayReply {
            items = List.copyOf(items);
        }
    }
}
