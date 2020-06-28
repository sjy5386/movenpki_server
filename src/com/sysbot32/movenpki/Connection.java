package com.sysbot32.movenpki;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Connection {
    private final SocketChannel socketChannel;
    public final SocketAddress socketAddress;
    private final ConnectionCallback callback;
    private ExecutorService executorService;
    private ByteBuffer receivedData = null;

    public Connection(SocketChannel socketChannel, ConnectionCallback callback) throws Exception {
        this.socketChannel = socketChannel;
        socketAddress = socketChannel.getRemoteAddress();
        this.callback = callback;
        executorService = Executors.newSingleThreadExecutor();
    }

    public void start() {
        executorService.submit(this::reading);
    }

    public void stop() {
        executorService.shutdownNow();
    }

    public ByteBuffer read() {
        if (!socketChannel.isConnected()) {
            return null;
        }

        ByteBuffer size = ByteBuffer.allocate(Integer.BYTES);
        ByteBuffer data;
        try {
            int ret = socketChannel.read(size);
            size.flip();
            if (ret == -1) {
                return null;
            }

            data = ByteBuffer.allocate(size.getInt());
            while (data.hasRemaining()) {
                socketChannel.read(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        data.flip();
        return data;
    }

    public void write(ByteBuffer data) {
        if (!socketChannel.isConnected()) {
            return;
        }

        new Thread(() -> {
            int size = data.capacity();
            ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES + size);
            byteBuffer.putInt(size);
            byteBuffer.put(data);
            byteBuffer.flip();
            try {
                socketChannel.write(byteBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void reading() {
        while (true) {
            ByteBuffer data = read();
            if (Objects.isNull(data)) {
                break;
            }
            receivedData = data;
            callback.received(this, data);
        }
        disconnect();
    }

    public void disconnect() {
        stop();
        try {
            socketChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        callback.disconnected(this);
    }

    public ByteBuffer getReceivedData() {
        return receivedData;
    }

    @Override
    public String toString() {
        return socketAddress.toString();
    }
}
