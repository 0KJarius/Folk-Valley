package menuPackage.startmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import mainpackage.MainFrame;
import menuPackage.OverlayMenu;
import stageselectpackage.StageSelectScreen;
import utilities.EventBus;
import utilities.GlobalConstants;
import utilities.SwingUtils;

public class LoadGameMenu extends OverlayMenu{
	private final String LOAD_PATH = "LoadNew/";
	private final String DELETE_BUTTON = LOAD_PATH + "delete/";
	private final String NUKE_BUTTON = LOAD_PATH + "nuke/";
	private final String LOAD_BUTTON = LOAD_PATH + "load/";
	private JButton nukeButton = SwingUtils.createJButton(NUKE_BUTTON + "normal.png", NUKE_BUTTON + "highlight.png", NUKE_BUTTON + "pressed.png", NUKE_BUTTON + "disabled.png");
	private JButton loadButton = SwingUtils.createJButton(LOAD_BUTTON + "normal.png", LOAD_BUTTON + "highlight.png", LOAD_BUTTON + "pressed.png", LOAD_BUTTON + "disabled.png");
	private JButton deleteButton = SwingUtils.createJButton(DELETE_BUTTON + "normal.png", DELETE_BUTTON + "pressed.png");
	
	public LoadGameMenu() {
		this.setOpaque(false);
		this.setBounds(new Rectangle(GlobalConstants.SCREEN_SIZE));
		JLayeredPane basePane = new JLayeredPane();
		this.setLayout(new BorderLayout());
		basePane.setPreferredSize(GlobalConstants.SCREEN_SIZE);
		this.add(basePane);
		
		JPanel contentPanel = SwingUtils.createJPanel(new Rectangle(310, 190, 662, 330), getBackground());
		setUpContentPanel(contentPanel);
		
		basePane.add(SwingUtils.createImageAt(LOAD_PATH + "loadScreen.png", new Point(0,0)), JLayeredPane.DEFAULT_LAYER);
		basePane.add(contentPanel, JLayeredPane.PALETTE_LAYER);
		this.addMouseListener(this);
		nukeButton.setEnabled(false);
		
		deleteButton.addActionListener(e -> onDeletePressed());
		nukeButton.addActionListener(e -> onNukePressed());
		loadButton.addActionListener(e -> onLoadPressed());
		
	}
	
	private void setUpContentPanel(JPanel content) {
		content.setOpaque(false);
		content.setLayout(new BorderLayout());
		JPanel bottomButtons = SwingUtils.createJPanel(new Dimension(0, 112), getBackground());
		bottomButtons.setOpaque(false);
		bottomButtons.add(nukeButton);
		bottomButtons.add(loadButton);
		
		JPanel bodyPanel = new JPanel();
		setUpBodyPanel(bodyPanel);
		
		content.add(bottomButtons, BorderLayout.SOUTH);
		content.add(bodyPanel, BorderLayout.CENTER);
		content.add(Box.createRigidArea(new Dimension(0, 90)), BorderLayout.NORTH);
	}
	
	private void setUpBodyPanel(JPanel body) {
		body.setOpaque(false);
		body.setLayout(new BorderLayout());
		JPanel terminal = SwingUtils.createJPanel(new Dimension(520, 0), getBackground());
		terminal.setOpaque(false);
		terminal.setLayout(new BorderLayout());
		
		JLabel nameLabel = SwingUtils.createJLabel(EventBus.player.getName(), 50, Color.white, GlobalConstants.VCR_MONO);
		JLabel moneyLabel = SwingUtils.createJLabel("" + EventBus.player.getCurrentMoney(), 90, new Color(255, 215, 47), GlobalConstants.NIKKYOU_SANS);
		moneyLabel.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 0));
		nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 160, 0, 0));
		terminal.add(nameLabel, BorderLayout.NORTH);
		terminal.add(moneyLabel, BorderLayout.SOUTH);
		
		body.add(deleteButton, BorderLayout.EAST);
		body.add(terminal, BorderLayout.WEST);
	}
	
	private void onDeletePressed() {
		nukeButton.setEnabled(true);
	}
	
	private void onNukePressed() {
		MainFrame.getInstance().removeLayer(this);
		MainFrame.getInstance().layerOnScreen(new NewGameMenu(), JLayeredPane.MODAL_LAYER);
	}
	
	private void onLoadPressed() {
		MainFrame.getInstance().removeLayer(this);
		MainFrame.getInstance().setScreen(StageSelectScreen.getInstance());
	}
}
