package res.layout;

import com.sysbot32.movenpki.Server;
import res.values.Fonts;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame frame;
    private JPanel contentPane;
    private BrowsingPanel browsingPanel;
    private ConnectionPanel connectionPanel;
    private ButtonPanel buttonPanel;
    private JLabel addressLabel;

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
        contentPane.add(connectionPanel.getPanel(), BorderLayout.WEST);
        contentPane.add(browsingPanel.getPanel(), BorderLayout.CENTER);
        contentPane.add(buttonPanel.getPanel(), BorderLayout.SOUTH);

        mainFrame = this;
    }

    public JFrame getFrame() {
        return frame;
    }

    public static MainFrame getMainFrame() {
        return mainFrame;
    }
}
