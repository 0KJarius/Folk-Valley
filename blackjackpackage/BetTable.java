package blackjackpackage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BetTable extends JPanel
{

    public BetTable(BlackJackScreen handler)
    {
        this.setBackground(Color.ORANGE);
        for (int i = 1; i < 6; i++)
        {
            int j = i;
            JButton betButton = new JButton("BET " + (i * 5));
            betButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handler.addBetPool(5 * j);
                }
                
            });

            this.add(betButton);
        }

        JButton confirmButton = new JButton("CONFIRM BET");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (handler.betPool == 0){
                    return;
                }
                handler.hideBetTable();
            }
        });
        this.add(confirmButton);
    }
}
