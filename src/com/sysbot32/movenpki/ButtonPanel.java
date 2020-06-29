package com.sysbot32.movenpki;

import javax.swing.*;
import java.awt.*;
import java.nio.ByteBuffer;
import java.util.Objects;

public class ButtonPanel {
    private JPanel panel;
    private JButton exportButton;
    private JButton importButton;

    private static ButtonPanel buttonPanel;

    public ButtonPanel() {
        panel = new JPanel();
        exportButton = new JButton("PC → 스마트폰");
        importButton = new JButton("스마트폰 → PC");

        panel.setLayout(new GridLayout(1, 2));
        exportButton.setFont(MainFrame.FONT_MALGUN);
        importButton.setFont(MainFrame.FONT_MALGUN);
        exportButton.setBackground(MainFrame.COLOR_EXPORT);
        importButton.setBackground(MainFrame.COLOR_IMPORT);

        panel.add(exportButton);
        panel.add(importButton);

        exportButton.addActionListener(e -> {
            String npkiPath = (String) BrowsingPanel.getBrowsingPanel().getPathComboBox().getSelectedItem();
            Connection connection = ConnectionPanel.getConnectionPanel().getConnectionList().getSelectedValue();
            if (Objects.isNull(connection)) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame().getFrame(), "공인인증서를 내보낼 스마트폰을 선택하세요.");
                return;
            }
            new Archiver().zip(npkiPath, "NPKI.zip");
            byte[] npki = new FileManager().read("NPKI.zip");
            Server.getServer().send(connection, ByteBuffer.wrap(npki));
            JOptionPane.showMessageDialog(MainFrame.getMainFrame().getFrame(), "완료되었습니다.");
        });
        importButton.addActionListener(e -> {
            String npkiPath = (String) BrowsingPanel.getBrowsingPanel().getPathComboBox().getSelectedItem();
            Connection connection = ConnectionPanel.getConnectionPanel().getConnectionList().getSelectedValue();
            if (Objects.isNull(connection)) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame().getFrame(), "공인인증서를 가져올 스마트폰을 선택하세요.");
                return;
            }
            ByteBuffer receivedData = connection.getReceivedData();
            if (Objects.isNull(receivedData)) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame().getFrame(), "스마트폰에서 < 스마트폰 → PC > 버튼을 터치하세요.");
                return;
            }
            byte[] npki = receivedData.array();
            new FileManager().write("NPKI.zip", npki);
            new Archiver().unzip("NPKI.zip", npkiPath);
            JOptionPane.showMessageDialog(MainFrame.getMainFrame().getFrame(), "완료되었습니다.");
        });

        buttonPanel = this;
    }

    public JPanel getPanel() {
        return panel;
    }

    public static ButtonPanel getButtonPanel() {
        return buttonPanel;
    }
}
