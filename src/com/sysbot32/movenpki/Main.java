package com.sysbot32.movenpki;

public class Main {
    private static MainFrame mainFrame;
    private static Server server;

    public static void main(String[] args) {
        mainFrame = new MainFrame();
        server = new Server();

        mainFrame.getFrame().setVisible(true);
        new Thread(() -> {
            server.accept();
        }).start();
    }

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    public static Server getServer() {
        return server;
    }
}
