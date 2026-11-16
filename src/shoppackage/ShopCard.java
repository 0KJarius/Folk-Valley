package shoppackage;

import blackjackpackage.cardpackage.*;

import javax.swing.*;
import java.awt.*;

import utilities.SwingUtils;
import utilities.EventBus;
import utilities.GlobalConstants;

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
            int imageWidth = shopItem instanceof shopConsumable ? 130 : 100;
            int imageHeight = 140;

            Image scaledImage = cardImage.getImage().getScaledInstance(
                imageWidth, imageHeight, Image.SCALE_SMOOTH
            );

            JLabel packLabel = new JLabel(new ImageIcon(scaledImage));
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
            new Dimension(150, 30), "Buy >.<", Color.PINK
        );

        buyButton.setToolTipText(description);

        buyButton.addActionListener(e -> {

            if (EventBus.player == null) {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Ur not found");
                return;
            }

            if (purchased) {
                messageLabel.setForeground(Color.YELLOW);
                messageLabel.setText("u bought tis already");
                return;
            }

            if (EventBus.player.getCurrentMoney() >= price) {
                EventBus.player.subMoney(price);

                purchased = true;
                buyButton.setEnabled(false);

                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText("Bought!");

                shopItem.buy();

            } else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Not enough money!");
            }
        });

        JPanel bottomPanel = SwingUtils.createJPanel(
            new Dimension(150, 80), BACKGROUND_COLOR
        );

        bottomPanel.setLayout(new GridLayout(3, 1));
        bottomPanel.add(priceLabel);
        bottomPanel.add(buyButton);
        bottomPanel.add(messageLabel);

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