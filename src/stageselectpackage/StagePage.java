package stageselectpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import blackjackpackage.BlackJackScreen;
import blackjackpackage.custom_matchpackage.tutorial.TutorialMatch;
import entitypackage.Entity;
import shoppackage.ShopTable;
import utilities.EventBus;

public class StagePage extends JPanel{
	public StagePage() {
		FlowLayout layout = new FlowLayout();
		layout.setVgap(0);
		this.setPreferredSize(new Dimension(673, 0));
		this.setLayout(layout);
		this.setOpaque(false);
		
		Stage testStage = new Stage("protosuer", new Color(255, 177, 177), "fight.png", false, new BlackJackScreen(new Entity(200, "Protosuer"), EventBus.player.playerDeck.cloneDeck()));
		Stage shopStage = new Stage("Shop", Color.white, "shop.png" ,true, new ShopTable(), testStage);
		Stage tutorial = new Stage("TUTORIAL", new Color(255, 177, 177), "fight.png", false, new TutorialMatch(new Entity(300, "Pursuer")), shopStage);
		
		this.add(tutorial);
		this.add(shopStage);
		this.add(testStage);
	}
}
