package com.sysbot32.movenpki;

import res.values.Fonts;

import javax.swing.*;
import java.awt.*;

public class ConnectionPanel {
    private JPanel panel;
    private JLabel label;
    private JList<Connection> connectionList;
    private JButton disconnectButton;

    private static ConnectionPanel connectionPanel;

    public ConnectionPanel() {
        panel = new JPanel();
        label = new JLabel("연결된 스마트폰: ");
        connectionList = new JList<>();
        disconnectButton = new JButton("연결 해제");

        panel.setLayout(new BorderLayout());
        label.setFont(Fonts.MALGUN.font);
        connectionList.setFont(Fonts.MALGUN.font);
        disconnectButton.setFont(Fonts.MALGUN.font);
        disconnectButton.setBackground(Color.LIGHT_GRAY);

        panel.add(label, BorderLayout.NORTH);
        panel.add(connectionList, BorderLayout.CENTER);
        panel.add(disconnectButton, BorderLayout.SOUTH);

        disconnectButton.addActionListener(e -> {
            connectionList.getSelectedValue().disconnect();
        });

        connectionPanel = this;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JList<Connection> getConnectionList() {
        return connectionList;
    }

    public static ConnectionPanel getConnectionPanel() {
        return connectionPanel;
    }
}
