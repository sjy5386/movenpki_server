package res.layout;

import com.sysbot32.movenpki.Archiver;
import com.sysbot32.movenpki.Connection;
import com.sysbot32.movenpki.FileManager;
import com.sysbot32.movenpki.Server;
import res.values.Colors;
import res.values.Fonts;

import javax.swing.*;
import java.awt.*;
import java.nio.ByteBuffer;
import java.util.Objects;

public class ButtonPanel {
    public final JPanel panel;
    public final JButton exportButton;
    public final JButton importButton;

    private static ButtonPanel buttonPanel;

    public ButtonPanel() {
        panel = new JPanel();
        exportButton = new JButton("PC → 스마트폰");
        importButton = new JButton("스마트폰 → PC");

        panel.setLayout(new GridLayout(1, 2));
        exportButton.setFont(Fonts.MALGUN.font);
        importButton.setFont(Fonts.MALGUN.font);
        exportButton.setBackground(Colors.EXPORT.color);
        importButton.setBackground(Colors.IMPORT.color);

        panel.add(exportButton);
        panel.add(importButton);

        exportButton.addActionListener(e -> {
            String npkiPath = (String) BrowsingPanel.getBrowsingPanel().pathComboBox.getSelectedItem();
            Connection connection = ConnectionPanel.getConnectionPanel().connectionList.getSelectedValue();
            if (Objects.isNull(connection)) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame().frame, "공인인증서를 내보낼 스마트폰을 선택하세요.");
                return;
            }
            new Archiver().zip(npkiPath, "NPKI.zip");
            byte[] npki = new FileManager().read("NPKI.zip");
            Server.getServer().send(connection, ByteBuffer.wrap(npki));
            JOptionPane.showMessageDialog(MainFrame.getMainFrame().frame, "완료되었습니다.");
        });
        importButton.addActionListener(e -> {
            String npkiPath = (String) BrowsingPanel.getBrowsingPanel().pathComboBox.getSelectedItem();
            Connection connection = ConnectionPanel.getConnectionPanel().connectionList.getSelectedValue();
            if (Objects.isNull(connection)) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame().frame, "공인인증서를 가져올 스마트폰을 선택하세요.");
                return;
            }
            ByteBuffer receivedData = connection.getReceivedData();
            if (Objects.isNull(receivedData)) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame().frame, "스마트폰에서 < 스마트폰 → PC > 버튼을 터치하세요.");
                return;
            }
            byte[] npki = receivedData.array();
            new FileManager().write("NPKI.zip", npki);
            new Archiver().unzip("NPKI.zip", npkiPath);
            JOptionPane.showMessageDialog(MainFrame.getMainFrame().frame, "완료되었습니다.");
        });

        buttonPanel = this;
    }

    public static ButtonPanel getButtonPanel() {
        return buttonPanel;
    }
}
