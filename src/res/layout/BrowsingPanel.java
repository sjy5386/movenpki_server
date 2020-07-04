package res.layout;

import res.values.Fonts;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class BrowsingPanel {
    private JPanel panel;
    private JLabel pathLabel;
    private JComboBox<String> pathComboBox;
    private JButton browseButton;

    private static BrowsingPanel browsingPanel;

    public BrowsingPanel() {
        panel = new JPanel();
        pathLabel = new JLabel("공인인증서 위치: ");
        pathComboBox = new JComboBox<>(new String[]{System.getProperty("user.home") + "\\AppData\\LocalLow\\NPKI", "C:\\Program Files\\NPKI"});
        browseButton = new JButton("찾아보기...");

        pathLabel.setFont(Fonts.MALGUN.font);
        pathComboBox.setFont(Fonts.MALGUN.font);
        pathComboBox.setBackground(Color.WHITE);
        File[] drives = File.listRoots();
        for (File drive : drives) {
            pathComboBox.addItem(drive.getPath() + "NPKI");
        }
        browseButton.setFont(Fonts.MALGUN.font);
        browseButton.setBackground(Color.WHITE);

        panel.add(pathLabel);
        panel.add(pathComboBox);
        panel.add(browseButton);

        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.showOpenDialog(MainFrame.getMainFrame().getFrame());
            File file = fileChooser.getSelectedFile();
            if (Objects.nonNull(file)) {
                pathComboBox.addItem(file.getPath());
                pathComboBox.setSelectedIndex(pathComboBox.getItemCount() - 1);
            }
        });

        browsingPanel = this;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JComboBox<String> getPathComboBox() {
        return pathComboBox;
    }

    public static BrowsingPanel getBrowsingPanel() {
        return browsingPanel;
    }
}
