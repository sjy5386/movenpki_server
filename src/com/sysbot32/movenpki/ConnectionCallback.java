package com.sysbot32.movenpki;

import java.nio.ByteBuffer;

public interface ConnectionCallback {
    void received(Connection connection, ByteBuffer data);

    void disconnected(Connection connection);
}
