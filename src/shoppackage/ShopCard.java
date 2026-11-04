package shoppackage;


import blackjackpackage.cardpackage.*;
import javax.swing.*;
import java.awt.*;
import utilities.SwingUtils;
import utilities.EventBus;
import utilities.GlobalConstants;

public class ShopCard extends JPanel{

	private ShopPack pack;
	private ShopInventory inventory;
	private int price;
	private JButton buyButton;
	private JLabel priceLabel;
	private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
	private JLabel messageLabel;
	private boolean purchased = false;
	
	public ShopCard(ShopPack pack, ShopInventory inventory) {
		super();
		this.pack = pack;
		this.price = pack.getPrice();
		this.inventory = inventory;
		
		this.setLayout(new BorderLayout(5, 5));
		this.setBackground(BACKGROUND_COLOR);
		this.setPreferredSize(new Dimension(150, 240));
		
		PokerCard previewCard = (PokerCard) pack.getCards().get(0);
		ImageIcon cardImage = previewCard.getCardImg();
		Image scaledImage = cardImage.getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
		
		JLabel packLabel = new JLabel(new ImageIcon(scaledImage));
		packLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.add(packLabel, BorderLayout.CENTER);
		
		priceLabel = SwingUtils.createJLabel("Price: " + price, 16, Color.RED, GlobalConstants.MARTIAN_MONO);
		messageLabel = SwingUtils.createJLabel(
			    " ", 12, Color.WHITE, GlobalConstants.MARTIAN_MONO
			);

			messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		buyButton = SwingUtils.createJButton(new Dimension(150, 30), "Buy >.<", Color.PINK);
		
		buyButton.addActionListener(e -> {
		    if (EventBus.player == null) {
		        messageLabel.setForeground(Color.RED);
		        messageLabel.setText("Ur not found");
		        return;
		    }
			
		    if(purchased) {
		    	messageLabel.setForeground(Color.YELLOW);
		    	messageLabel.setText("u bought tis already");
		    	return;
		    }
		    
			if(EventBus.player.getCurrentMoney() >= price) {
				EventBus.player.subMoney(price);
				purchased = true;
				
				buyButton.setEnabled(false);
				
				messageLabel.setForeground(Color.green);
				messageLabel.setText("Bought!");
				inventory.showSelect(pack, EventBus.player);
			} else {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("Not enough money!");
			}

			
		});
		
		
		JPanel bottomPanel = SwingUtils.createJPanel(new Dimension(150, 80), BACKGROUND_COLOR);
		bottomPanel.setLayout(new GridLayout(3,1));
		
		bottomPanel.add(priceLabel);
		bottomPanel.add(buyButton);
		bottomPanel.add(messageLabel);
		
		this.add(bottomPanel, BorderLayout.SOUTH);
	}
	public ShopPack getPack() {
		return pack;
	}
	public int getPrice() {
		return price;
	}
	public JButton getBuyButton() {
		return buyButton;
	}
	
	
	
}
