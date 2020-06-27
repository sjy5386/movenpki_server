package com.sysbot32.movenpki;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Objects;

public class Server {
    public static final int PORT = 13681;

    private ServerSocketChannel serverSocketChannel;
    private SocketChannel socketChannel;

    public Server() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(PORT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void accept() {
        try {
            socketChannel = serverSocketChannel.accept();
            System.out.println(socketChannel.getRemoteAddress() + " is connected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(ByteBuffer data) {
        if (!isConnected()) {
            return;
        }

        int size = data.capacity();
        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES + size);
        byteBuffer.putInt(size);
        byteBuffer.put(data);
        data.flip();
        try {
            socketChannel.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ByteBuffer receive() {
        if (!isConnected()) {
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

    public boolean isConnected() {
        return Objects.nonNull(socketChannel) && socketChannel.isConnected();
    }

    public static String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
