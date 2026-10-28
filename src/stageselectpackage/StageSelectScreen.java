package stageselectpackage;

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
import utilities.EventBus;
import utilities.SwingUtils;

public class StageSelectScreen extends JPanel{
	private static final StageSelectScreen STAGE_SELECT = new StageSelectScreen();
	private JLayeredPane basePane = new JLayeredPane();
	private JLabel balanceLabel;
	private JPanel contentPanel = SwingUtils.createJPanel(new Rectangle(1280, 720), getBackground());
	
	private final String DECK_BUTTON_PATH = "stageSelect/deck/";
	
	private StageSelectScreen() {
		this.setBounds(new Rectangle(1280, 720));
		this.setLayout(new BorderLayout());
		basePane.setPreferredSize(this.getSize());
		this.add(basePane);
		setUpContentPanel();
		
		
		basePane.add(SwingUtils.createImageAt("stageSelect/bg.png", new Point(0, 0)), JLayeredPane.DEFAULT_LAYER);
		basePane.add(contentPanel, JLayeredPane.PALETTE_LAYER);
	}
	
	private void setUpContentPanel() {
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setOpaque(false);
		
		JPanel topHud = SwingUtils.createJPanel(new Dimension(0, 139), getBackground());
		setUpTopHud(topHud);
		
		JPanel rightHud = SwingUtils.createJPanel(new Dimension(344, 0), getBackground());
		setUpRightHud(rightHud);
		
		JPanel centerHud = SwingUtils.createJPanel(new Dimension(673, 0), getBackground());
		setUpCenterHud(centerHud);
		
		contentPanel.add(topHud, BorderLayout.NORTH);
		contentPanel.add(rightHud, BorderLayout.EAST);
		contentPanel.add(Box.createRigidArea(new Dimension(267, 0)), BorderLayout.WEST);
		contentPanel.add(centerHud, BorderLayout.CENTER);
	}
	
	private void setUpTopHud(JPanel top) {
		top.setLayout(new BorderLayout());
		top.setOpaque(false);
		balanceLabel = SwingUtils.createJLabel("V" + EventBus.player.getCurrentMoney(), 44, Color.decode("#FFD72F"), SwingUtils.VCR_MONO);
		balanceLabel.setBorder(BorderFactory.createEmptyBorder(38, 35, 0, 0));
		top.add(balanceLabel, BorderLayout.WEST);
	}
	
	private void setUpRightHud(JPanel right) {
		right.setLayout(new BorderLayout());
		right.setOpaque(false);
		
		JButton viewDeckButton = SwingUtils.createJButton(
				DECK_BUTTON_PATH + "normal.png", 
				DECK_BUTTON_PATH + "highlight.png", 
				DECK_BUTTON_PATH + "pressed.png", 
				DECK_BUTTON_PATH + "disable.png"
		);
		right.add(viewDeckButton, BorderLayout.SOUTH);
		right.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
	}
	
	private void setUpCenterHud(JPanel center) {
		center.setOpaque(false);
		center.setLayout(new BorderLayout());
		center.add(new StagePage());
	}
	
	public static StageSelectScreen getInstance() {
		return STAGE_SELECT;
	}
	

}
