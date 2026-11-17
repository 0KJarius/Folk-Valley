package shoppackage;
import javax.swing.*;

import java.awt.*;
import blackjackpackage.cardpackage.*;
import utilities.GlobalConstants;
import utilities.SwingUtils;
import utilities.EventBus;
import entitypackage.Player;
import java.util.Random;
import itempackage.*;
import itempackage.colorpicker.ColorPicker;

import java.util.ArrayList;


public class ShopInventory extends JPanel{
	private final static Color BACKGROUND = Color.BLACK;
	private JPanel body;
	private ShopCard card1;
	private ShopCard card2;
	private ShopCard card3;
	private ShopCard card4;
	private Random random = new Random();
	private int rerollPrice = 0;

	private shopItem generateConsumable() {

		ArrayList<Item> items = new ArrayList<>();

		items.add(new Flip());
		items.add(new PlusTwo());
		items.add(new Reverse());
		items.add(new ColorPicker());

		int roll = random.nextInt(4) + 1;
    
		for (Item item : items) {

			int rarity = 0;

			if (item instanceof Flip) {
				rarity = 3;
			} else if (item instanceof PlusTwo) {
				rarity = 1;
			} else if (item instanceof Reverse) {
				rarity = 2;
			} else if (item instanceof ColorPicker) {
				rarity = 4;
			}

			if (roll == rarity) {
				return new shopConsumable(
						75,
						item.getDescription(),
						rarity,
						item
				);
			}
		}

		return null;
	}
	

	private shopItem generateShopItem() {
		int chance = random.nextInt(4);
	
		switch (chance) {
	
		case 0:
			// ITEMS / CONSUMABLES
			return generateConsumable();
	
		case 1:
			// JOHN POKER PACK
			ShopPack pokerPack = new ShopPack(100, ShopPack.PackType.POKER);
		
			return new ShopCardPack(100, "Poker Pack", 1, pokerPack, this);
		
		case 2:
			// JOHN UNO PACK
			ShopPack unoPack = new ShopPack(100, ShopPack.PackType.UNO);
		
			return new ShopCardPack(100, "UNO Pack", 1, unoPack, this);
		
		case 3:
			// JOHN POKER PACK
			ShopPack mixedPack = new ShopPack(100, ShopPack.PackType.MIXED);
		
			return new ShopCardPack(100, "Mixed Pack", 1, mixedPack, this);
		
		}
	
		return null;
	}


	public ShopInventory() {
		super();
		this.setPreferredSize(new Dimension(967, 530));
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(10, 4, 10, 10));
		body = SwingUtils.createJPanel(new Dimension(0, 0), BACKGROUND);
		body.setOpaque(false);
		body.setLayout(new GridLayout(0, 4, 10, 10));
		
        createShopCards();

        
        this.add(body, BorderLayout.CENTER);
	}
		//TEMPCARDD
		
        private void createShopCards() {
            card1 = new ShopCard(generateShopItem(), this);
            card2 = new ShopCard(generateShopItem(), this);
            card3 = new ShopCard(generateShopItem(), this);
            card4 = new ShopCard(generateShopItem(), this);

            body.add(card1);
            body.add(card2);
            body.add(card3);
            body.add(card4);
        	
        	
        }
        
        public void reroll() {
        	body.removeAll();
        	createShopCards();
        	
        	body.revalidate();
        	body.repaint();
        	EventBus.player.subMoney(getRerollPrice());
        	rerollPrice = getRerollPrice() == 0 ? 50 : (int) (getRerollPrice() * 1.5);
        }


    
	
	
        public void showSelect(ShopPack pack, Player player) {
        	this.remove(body);
		
        	JPanel selectPanel = SwingUtils.createJPanel(new Dimension(700, 500), BACKGROUND);
        	selectPanel.setOpaque(false);
        	selectPanel.setLayout(new BorderLayout(10, 10));
		
        	JLabel title = SwingUtils.createJLabel("CHOOSE A CARD", 20, Color.CYAN, GlobalConstants.MARTIAN_MONO);
        	title.setHorizontalAlignment(SwingConstants.CENTER);
        	selectPanel.add(title, BorderLayout.NORTH);
		
        	JPanel cardPanel = SwingUtils.createJPanel(new Dimension(700, 400), BACKGROUND);
        	cardPanel.setOpaque(false);
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


	public int getRerollPrice() {
		return rerollPrice;
	}
	

	
	
	
	
}
