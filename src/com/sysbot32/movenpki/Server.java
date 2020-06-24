package com.sysbot32.movenpki;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

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
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println(socketChannel.getRemoteAddress() + "is connected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(ByteBuffer data) {
        if (!socketChannel.isConnected()) {
            return;
        }

        data.flip();
        try {
            socketChannel.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ByteBuffer receive() {
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
}
