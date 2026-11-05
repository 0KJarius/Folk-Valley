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
	public StagePage(Stage ...stages) {
		FlowLayout layout = new FlowLayout();
		layout.setVgap(0);
		this.setPreferredSize(new Dimension(673, 0));
		this.setLayout(layout);
		this.setOpaque(false);
		
		for (Stage stage : stages) {
			this.add(stage);
		}
	}
}
