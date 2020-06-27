package com.sysbot32.movenpki;

import javax.swing.*;
import java.awt.*;

public class ConnectionPanel {
    private JPanel panel;
    private JLabel label;
    private JList<Connection> connectionList;
    private JButton disconnectButton;

    public ConnectionPanel() {
        panel = new JPanel();
        label = new JLabel("연결된 스마트폰: ");
        connectionList = new JList<>();
        disconnectButton = new JButton("연결 해제");

        panel.setLayout(new BorderLayout());
        label.setFont(MainFrame.FONT_MALGUN);
        connectionList.setFont(MainFrame.FONT_MALGUN);
        disconnectButton.setFont(MainFrame.FONT_MALGUN);
        disconnectButton.setBackground(Color.LIGHT_GRAY);

        panel.add(label, BorderLayout.NORTH);
        panel.add(connectionList, BorderLayout.CENTER);
        panel.add(disconnectButton, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {
        return panel;
    }
}
