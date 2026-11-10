package shoppackage;
import javax.swing.*;

import java.awt.*;
import blackjackpackage.cardpackage.*;
import utilities.GlobalConstants;
import utilities.SwingUtils;
import utilities.EventBus;
import entitypackage.Player;

public class ShopInventory extends JPanel{
private final static Color BACKGROUND = Color.BLACK;
private JPanel body;
private ShopCard card1;
private ShopCard card2;
private ShopCard card3;
private ShopCard card4;

private JButton rerollButton;
private int rerollPrice = 0;

	public ShopInventory() {
		super();
		
		this.setLayout(new BorderLayout());
		
		body = SwingUtils.createJPanel(new Dimension(700, 500), BACKGROUND);
		body.setLayout(new GridLayout(0, 4, 10, 10));
		
        createShopCards();

		
		rerollButton = SwingUtils.createJButton(new Dimension(120, 40), "Reroll", Color.PINK);
        rerollButton.addActionListener(e -> {
        	if (EventBus.player.getCurrentMoney() >= rerollPrice) {
        		reroll();
        	}
        });
        
        this.add(body, BorderLayout.CENTER);
        this.add(rerollButton, BorderLayout.SOUTH);
		
	}
		//TEMPCARDD
		
        private void createShopCards() {
        	ShopPack pack1 = new ShopPack(100);
            ShopPack pack2 = new ShopPack(100);
            ShopPack pack3 = new ShopPack(100);
            ShopPack pack4 = new ShopPack(100);

             
             card1 = new ShopCard(pack1, this);
             card2 = new ShopCard(pack2, this);
             card3 = new ShopCard(pack3, this);
             card4 = new ShopCard(pack4, this);


            body.add(card1);
            body.add(card2);
            body.add(card3);
            body.add(card4);
        	
        	
        }
        
        private void reroll() {
        	body.removeAll();
        	createShopCards();
        	
        	body.revalidate();
        	body.repaint();
        	EventBus.player.subMoney(rerollPrice);
        	rerollPrice = rerollPrice == 0 ? 50 : (int) (rerollPrice * 1.5);
        }


    
	
	
	public void showSelect(ShopPack pack, Player player) {
		this.remove(body);
		this.remove(rerollButton);
		
		JPanel selectPanel = SwingUtils.createJPanel(new Dimension(700, 500), BACKGROUND);
		selectPanel.setLayout(new BorderLayout(10, 10));
		
		JLabel title = SwingUtils.createJLabel("CHOOSE A CARD", 20, Color.CYAN, GlobalConstants.MARTIAN_MONO);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		selectPanel.add(title, BorderLayout.NORTH);
		
		JPanel cardPanel = SwingUtils.createJPanel(new Dimension(700, 400), BACKGROUND);
		cardPanel.setLayout(new GridLayout(1,5,10,10));
		
		for (Card card : pack.getCards()) {
			card.addActionListener(e -> {
				player.playerDeck.addCard(card);
				this.remove(selectPanel);
				this.add(body, BorderLayout.CENTER);
				this.add(rerollButton, BorderLayout.SOUTH);
				this.revalidate();
				this.repaint();
			});
			
			cardPanel.add(card);
		}
		
		selectPanel.add(cardPanel, BorderLayout.CENTER);
		this.add(selectPanel, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
	
	
	
	
	
	
}
