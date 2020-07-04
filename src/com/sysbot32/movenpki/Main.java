package com.sysbot32.movenpki;

import res.layout.MainFrame;

public class Main {
    public static void main(String[] args) {
        new MainFrame().getFrame().setVisible(true);
        new Server().start();
    }
}
