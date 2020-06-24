package com.sysbot32.movenpki;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    public static final Font MALGUN = new Font("맑은 고딕", Font.PLAIN, 16);
    public static final Color EXPORT_COLOR = new Color(0x42a5f5);
    public static final Color IMPORT_COLOR = new Color(0xef5350);

    private JFrame frame;
    private JPanel contentPane;
    private JButton exportButton;
    private JButton importButton;

    public MainFrame() {
        frame = new JFrame("MoveNPKI");
        contentPane = (JPanel) frame.getContentPane();
        exportButton = new JButton("PC → 스마트폰");
        importButton = new JButton("스마트폰 → PC");

        frame.setSize(250, 120);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new FlowLayout());
        exportButton.setFont(MainFrame.MALGUN);
        importButton.setFont(MainFrame.MALGUN);
        exportButton.setBackground(MainFrame.EXPORT_COLOR);
        importButton.setBackground(MainFrame.IMPORT_COLOR);

        contentPane.add(exportButton);
        contentPane.add(importButton);
    }

    public JFrame getFrame() {
        return frame;
    }
}
