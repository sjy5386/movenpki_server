package com.sysbot32.movenpki;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    public static final Font FONT_MALGUN = new Font("맑은 고딕", Font.PLAIN, 16);
    public static final Color COLOR_EXPORT = new Color(0x42a5f5);
    public static final Color COLOR_IMPORT = new Color(0xef5350);

    private JFrame frame;
    private JPanel contentPane;
    private JLabel addressLabel;
    private JButton exportButton;
    private JButton importButton;

    public MainFrame() {
        frame = new JFrame("MoveNPKI");
        contentPane = (JPanel) frame.getContentPane();
        addressLabel = new JLabel(Server.getHostAddress());
        exportButton = new JButton("PC → 스마트폰");
        importButton = new JButton("스마트폰 → PC");

        frame.setSize(250, 150);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new FlowLayout());
        addressLabel.setFont(FONT_MALGUN);
        exportButton.setFont(FONT_MALGUN);
        importButton.setFont(FONT_MALGUN);
        exportButton.setBackground(COLOR_EXPORT);
        importButton.setBackground(COLOR_IMPORT);

        contentPane.add(addressLabel);
        contentPane.add(exportButton);
        contentPane.add(importButton);
    }

    public JFrame getFrame() {
        return frame;
    }
}
