package com.sysbot32.movenpki;

import javax.swing.*;
import java.awt.*;

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

        buttonPanel = this;
    }

    public JPanel getPanel() {
        return panel;
    }

    public static ButtonPanel getButtonPanel() {
        return buttonPanel;
    }
}
