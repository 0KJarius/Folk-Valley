package blackjackpackage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import utilites.EventBus;

public class BetTable extends JPanel
{

    public BetTable()
    {
    	
    	this.setBounds(270, 350, 765, 300);
        this.setBackground(Color.ORANGE);
        for (int i = 1; i < 6; i++)
        {
            int j = i;
            JButton betButton = new JButton("BET " + (i * 5));
            betButton.setFocusPainted(false);
            betButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EventBus.currentMatch.addBetPool(5 * j);
                }
                
            });

            this.add(betButton);
        }

        JButton confirmButton = new JButton("CONFIRM BET");
        confirmButton.setFocusPainted(false);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	confirmBet();
            }
        });
        this.add(confirmButton);
    }
    
    private void confirmBet() {
    	if (EventBus.currentMatch.betPool == 0){
            return;
        }
        EventBus.frame.removeLayer(this);
    }
    
}
