/**
 * Main runnable class.
 * <p>
 * see WhiteBoard.java for more info.
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // using a modern-looking UI format (Nimbus)
        try {
            // Set Nimbus as the UI manager for the application
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                // if Nimbus is available, use it
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus isn't available, keep the default
        }
        // Create the UI on the Event Dispatch Thread
        SwingUtilities.invokeLater(WhiteBoard::new);
    }
}
