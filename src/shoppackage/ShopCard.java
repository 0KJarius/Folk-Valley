package shoppackage;

import blackjackpackage.cardpackage.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import utilities.SwingUtils;
import utilities.EventBus;
import utilities.GlobalConstants;
import utilities.SoundBus;

public class ShopCard extends JPanel {

    private shopItem shopItem;
    private ShopInventory inventory;
    private int price;
    private JButton buyButton;
    private JLabel priceLabel;
    private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    private JLabel messageLabel;
    private boolean purchased = false;

    public ShopCard(shopItem shopItem, ShopInventory inventory) {
        super();

        this.shopItem = shopItem;
        this.price = shopItem.getPrice();
        this.inventory = inventory;

        String description = shopItem.getDescription();

        this.setLayout(new BorderLayout(5, 5));
        this.setBackground(BACKGROUND_COLOR);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(150, 240));

        // Display item image
        ImageIcon cardImage = null;

        if (shopItem instanceof ShopCardPack) {
        	ShopPack pack = ((ShopCardPack) shopItem).getPack();
        	
        	switch (pack.getPackType()) {
        	case POKER:
        		cardImage = SwingUtils.getImage("Shop/packs/poker.png");
        		break;
        	case UNO:
        		cardImage = SwingUtils.getImage("Shop/packs/ono.png");
        		break;	
        	case MIXED:
        		cardImage = SwingUtils.getImage("Shop/packs/mix.png");
        		break;
        		
        	}
       
        } else if (shopItem instanceof shopConsumable) {
        	shopConsumable consumable = (shopConsumable) shopItem;
        	cardImage = consumable.getItem().getItemImg();
        }

        if (cardImage != null) {

            JLabel packLabel = new JLabel(cardImage);
            packLabel.setHorizontalAlignment(SwingConstants.CENTER);
            packLabel.setToolTipText(description);

            this.add(packLabel, BorderLayout.CENTER);
            ToolTipManager.sharedInstance().registerComponent(packLabel);
        }

        // PRICE
        priceLabel = SwingUtils.createJLabel(
            "Price: " + price, 16, Color.RED, GlobalConstants.MARTIAN_MONO
        );

        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setToolTipText(description);

        // Message
        messageLabel = SwingUtils.createJLabel(
            " ", 12, Color.WHITE, GlobalConstants.MARTIAN_MONO
        );

        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setToolTipText(description);

        // BUY BTN
        buyButton = SwingUtils.createJButton(
            new Dimension(235, 56), "Buy >.<", Color.PINK
        );

        buyButton.setToolTipText(description);
        SwingUtils.addButtonSFX(buyButton, "shop/hover.wav", "", "");

        buyButton.addActionListener(e -> {

            if (EventBus.player == null) {
            	priceLabel.setForeground(Color.RED);
            	priceLabel.setText("Ur not found");
                return;
            }

            if (purchased) {
            	priceLabel.setForeground(Color.YELLOW);
            	priceLabel.setText("u bought tis already");
                return;
            }

            if (EventBus.player.getCurrentMoney() > price) {
                EventBus.player.subMoney(price);

                purchased = true;
                buyButton.setEnabled(false);

                priceLabel.setForeground(Color.GREEN);
                priceLabel.setText("Bought!");
                
                shopItem.buy();
				EventBus.currentShop.onBuySomething();
            } else {
            	priceLabel.setForeground(Color.RED);
            	priceLabel.setText("Not enough money!");
            	try {
					SoundBus.playSFX("button/decline.wav");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {}
            }
        });

        JPanel bottomPanel = SwingUtils.createJPanel(
            new Dimension(150, 80), BACKGROUND_COLOR
        );
        bottomPanel.setOpaque(false);
        
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(priceLabel, BorderLayout.NORTH);
        bottomPanel.add(buyButton);

        this.add(bottomPanel, BorderLayout.SOUTH);

        // Register tooltips
        this.setToolTipText(description);
        ToolTipManager.sharedInstance().registerComponent(this);
        ToolTipManager.sharedInstance().registerComponent(priceLabel);
        ToolTipManager.sharedInstance().registerComponent(messageLabel);
    }

    public shopItem getShopItem() {
        return shopItem;
    }

    public int getPrice() {
        return price;
    }

    public JButton getBuyButton() {
        return buyButton;
    }
}