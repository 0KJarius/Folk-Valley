package shoppackage;

import javax.swing.*;
import java.awt.*;

public class ShopTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FOLKVALLEY SHOP TEST");

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setLayout(new BorderLayout());
    		frame.setResizable(false);
            ShopTable shop = new ShopTable();
            frame.add(shop, BorderLayout.CENTER);


            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
