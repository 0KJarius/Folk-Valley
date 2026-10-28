package shoppackage;

import javax.swing.*;
import java.awt.*;
import entitypackage.Player;
public class ShopTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Folk Valley - Shop Test");
            Player player = new Player(1000, "Test");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());
    		frame.setResizable(false);
            ShopTable shop = new ShopTable();
            frame.add(shop, BorderLayout.CENTER);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
