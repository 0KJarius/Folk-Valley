package menuPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import entitypackage.Enemy;
import mainpackage.MainFrame;
import utilities.GlobalConstants;
import utilities.SwingUtils;

public class BattlePrompt extends OverlayMenu{
	private final String BATTLE_PROMPT_PATH = "battlePrompt/";
	private Enemy opp;
	private JPanel screenTransition;
	
	public BattlePrompt(Enemy opp, JPanel screenTransition) {
		this.opp = opp;
		this.screenTransition = screenTransition;
		this.setOpaque(false);
		this.setBounds(new Rectangle(GlobalConstants.SCREEN_SIZE));
		this.setLayout(new BorderLayout());
		JLayeredPane basePane = new JLayeredPane();
		basePane.setPreferredSize(getPreferredSize());
		this.add(basePane);
		JPanel contentPanel = SwingUtils.createJPanel(new Rectangle(237, 115, 708, 435), getBackground());
		setUpContent(contentPanel);
		
		basePane.add(SwingUtils.createImageAt(BATTLE_PROMPT_PATH + "battlePrompBG.png", new Point(0,0)));
		basePane.add(contentPanel, JLayeredPane.PALETTE_LAYER);
		basePane.add(SwingUtils.createImageAt(BATTLE_PROMPT_PATH + "battlePromptScanLines.png", new Point(0,0)), JLayeredPane.MODAL_LAYER);
	}
	
	private void setUpContent(JPanel content) {
		content.setOpaque(false);
		content.setLayout(new BorderLayout());
		JPanel header = SwingUtils.createJPanel(new Dimension(0, 222), getBackground());
		setUpHeader(header);
		
		JPanel footer = SwingUtils.createJPanel(new Dimension(0, 213), getBackground());
		setUpFooter(footer);
		
		content.add(header, BorderLayout.NORTH);
		content.add(footer, BorderLayout.SOUTH);
	}
	
	private void setUpFooter(JPanel footer) {
		footer.setOpaque(false);
		footer.setLayout(new BorderLayout());
		
		JPanel textContainer = SwingUtils.createJPanel(new Dimension(550, 0), getBackground());
		setUpTextContainer(textContainer);
		
		JPanel buttonContainer = SwingUtils.createJPanel(new Dimension(158, 0), getBackground());
		setUpButtonContaner(buttonContainer);
		
		footer.add(textContainer, BorderLayout.WEST);
		footer.add(buttonContainer, BorderLayout.EAST);
	}
	
	private void setUpTextContainer(JPanel textContainer) {
		textContainer.setOpaque(false);
		textContainer.setBorder(BorderFactory.createEmptyBorder(7, 19, 0, 0));
		JTextArea descriptionText = SwingUtils.creatJTextArea(new Dimension(524, 184), GlobalConstants.NOTO_LIGHT, 20, 5, Color.white, getBackground());
		descriptionText.setText(opp.getDescription());
		descriptionText.setOpaque(false);
		
		textContainer.add(descriptionText);
	}
	
	private void setUpButtonContaner(JPanel container) {
		container.setOpaque(false);
		container.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		JButton acceptButton = SwingUtils.createJButton(BATTLE_PROMPT_PATH + "accept/normal.png", BATTLE_PROMPT_PATH + "accept/pressed.png");
		JButton declineButton = SwingUtils.createJButton(BATTLE_PROMPT_PATH + "decline/normal.png", BATTLE_PROMPT_PATH + "decline/pressed.png");
		
		acceptButton.addActionListener(e -> {
			MainFrame.getInstance().removeLayer(this);
			MainFrame.getInstance().setScreen(screenTransition);
		});
		
		declineButton.addActionListener(e -> {
			MainFrame.getInstance().removeLayer(this);
		});
		
		container.add(acceptButton);
		container.add(declineButton);
	}
	
	private void setUpHeader(JPanel header) {
		header.setOpaque(false);
		header.setLayout(new BorderLayout());
		
		
		JPanel portraitContainer = SwingUtils.createJPanel(new Dimension(185, 0), getBackground());
		setUpPortrait(portraitContainer);
		
		JPanel infoContainer = SwingUtils.createJPanel(new Dimension(523, 0), getBackground());
		setUpInfo(infoContainer);
		
		
		header.add(portraitContainer, BorderLayout.WEST);
		header.add(infoContainer, BorderLayout.EAST);
	}
	
	private void setUpInfo(JPanel info) {
		info.setOpaque(false);
		info.setLayout(new BorderLayout());
		
		JLabel nameLabel = SwingUtils.createJLabel(opp.getName(), 60, new Color(29, 39, 43), GlobalConstants.RUBIC_MONO);
		JLabel moneyLabel = SwingUtils.createJLabel("V" + opp.getMaxMoney(), 115, new Color(255, 215, 47), GlobalConstants.VCR_MONO);
		info.add(nameLabel, BorderLayout.NORTH);
		info.add(moneyLabel, BorderLayout.SOUTH);
	}
	
	private void setUpPortrait(JPanel portraitContainer) {
		FlowLayout containerLayout = new FlowLayout(FlowLayout.RIGHT);
		containerLayout.setVgap(16);
		portraitContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));
		
		JLabel portrait = new JLabel(opp.getPortrait());
		portrait.setPreferredSize(new Dimension(157, 204));
		portrait.setOpaque(true);
		portrait.setBackground(Color.BLACK);
		
		portraitContainer.setLayout(containerLayout);
		portraitContainer.setOpaque(false);
		portraitContainer.add(portrait);
		
	}
}
