package res.layout;

import com.sysbot32.movenpki.Server;
import res.values.Fonts;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    public final JFrame frame;
    public final JPanel contentPane;
    public final BrowsingPanel browsingPanel;
    public final ConnectionPanel connectionPanel;
    public final ButtonPanel buttonPanel;
    public final JLabel addressLabel;

    private static MainFrame mainFrame;

    public MainFrame() {
        frame = new JFrame("MoveNPKI");
        contentPane = (JPanel) frame.getContentPane();
        browsingPanel = new BrowsingPanel();
        connectionPanel = new ConnectionPanel();
        buttonPanel = new ButtonPanel();
        addressLabel = new JLabel(Server.getHostAddress());

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new BorderLayout());
        addressLabel.setFont(Fonts.MALGUN.font);

        contentPane.add(addressLabel, BorderLayout.NORTH);
        contentPane.add(connectionPanel.panel, BorderLayout.WEST);
        contentPane.add(browsingPanel.panel, BorderLayout.CENTER);
        contentPane.add(buttonPanel.panel, BorderLayout.SOUTH);

        mainFrame = this;
    }

    public static MainFrame getMainFrame() {
        return mainFrame;
    }
}
