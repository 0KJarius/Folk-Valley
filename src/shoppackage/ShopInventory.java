package shoppackage;
import javax.swing.*;

import java.awt.*;
import blackjackpackage.cardpackage.*;
import utilites.SwingUtils;
import entitypackage.Player;

public class ShopInventory extends JPanel{
private final static Color BACKGROUND = Color.BLACK;
private JPanel body;
private ShopCard card1;
private ShopCard card2;
private ShopCard card3;



	public ShopInventory() {
		super();
		
		this.setLayout(new BorderLayout());
		
		body = SwingUtils.createJPanel(new Dimension(700, 500), BACKGROUND);
		body.setLayout(new GridLayout(0, 3, 10, 10));
		
		//TEMPCARDD
		

        ShopPack pack1 = new ShopPack(100);
        ShopPack pack2 = new ShopPack(150);
        ShopPack pack3 = new ShopPack(200);

         
         card1 = new ShopCard(pack1, this);
         card2 = new ShopCard(pack2, this);
         card3 = new ShopCard(pack3, this);


        body.add(card1);
        body.add(card2);
        body.add(card3);


        this.add(body, BorderLayout.CENTER);
    }
	
	
	public void showSelect(ShopPack pack, Player player) {
		this.remove(body);
		
		JPanel selectPanel = SwingUtils.createJPanel(new Dimension(700, 500), BACKGROUND);
		selectPanel.setLayout(new BorderLayout(10, 10));
		
		JLabel title = SwingUtils.createJLabel("CHOOSE A CARD", 20, Color.CYAN, SwingUtils.MARTIAN_MONO);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		selectPanel.add(title, BorderLayout.NORTH);
		
		JPanel cardPanel = SwingUtils.createJPanel(new Dimension(700, 400), BACKGROUND);
		cardPanel.setLayout(new GridLayout(1,5,10,10));
		
		for (Card card : pack.getCards()) {
			card.addActionListener(e -> {
				player.playerDeck.addCard(card);
				this.remove(selectPanel);
				this.add(body, BorderLayout.CENTER);
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
