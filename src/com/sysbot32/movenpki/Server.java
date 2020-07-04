package com.sysbot32.movenpki;

import res.layout.ConnectionPanel;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements ConnectionCallback {
    public static final int PORT = 13681;

    private ServerSocketChannel serverSocketChannel;
    private List<Connection> connections;
    private ExecutorService executorService;

    private static Server server;

    public Server() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(PORT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        connections = new ArrayList<>();
        executorService = Executors.newSingleThreadExecutor();

        server = this;
    }

    public void start() {
        executorService.submit(this::accepting);
    }

    public void stop() {
        executorService.shutdownNow();
    }

    public void accept() {
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            Connection connection = new Connection(socketChannel, this);
            connections.add(connection);
            connection.start();
            System.out.println(connection.socketAddress + " is connected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectionPanel.getConnectionPanel().getConnectionList().setListData(getConnections());
    }

    private void accepting() {
        while (true) {
            accept();
        }
    }

    public void send(Connection connection, ByteBuffer data) {
        connection.write(data);
    }

    public boolean isConnected() {
        return connections.size() > 0;
    }

    @Override
    public void received(Connection connection, ByteBuffer data) {
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println(connection.socketAddress + " is disconnected.");
        connections.remove(connection);
        ConnectionPanel.getConnectionPanel().getConnectionList().setListData(getConnections());
    }

    public Connection[] getConnections() {
        return connections.toArray(new Connection[0]);
    }

    public static String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Server getServer() {
        return server;
    }
}
