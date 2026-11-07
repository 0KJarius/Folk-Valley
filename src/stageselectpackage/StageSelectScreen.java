package stageselectpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import mainpackage.MainFrame;
import menuPackage.ViewDeckScreen;
import utilities.EventBus;
import utilities.GlobalConstants;
import utilities.SoundBus;
import utilities.SwingUtils;

public class StageSelectScreen extends JPanel{
	private static final StageSelectScreen STAGE_SELECT = new StageSelectScreen();
	private JLayeredPane basePane = new JLayeredPane();
	private JPanel centerHud;
	private JLabel balanceLabel;
	private JPanel contentPanel = SwingUtils.createJPanel(new Rectangle(1280, 720), getBackground());
	private StagePage[] pages = {
			StageCollection.createStagePage(StageCollection.stages.size() - 1, StageCollection.stages.size() - 5),
			//StageCollection.createStagePage(StageCollection.stages.size() - 6, StageCollection.stages.)
	};
	
	private StagePage currentPage;
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
		
		centerHud = SwingUtils.createJPanel(new Dimension(673, 0), getBackground());
		setUpCenterHud(centerHud);
		
		JPanel leftHud = SwingUtils.createJPanel(new Dimension(267, 0), getBackground());
		setUpLeftHud(leftHud);
		
		contentPanel.add(topHud, BorderLayout.NORTH);
		contentPanel.add(rightHud, BorderLayout.EAST);
		contentPanel.add(leftHud, BorderLayout.WEST);
		contentPanel.add(centerHud, BorderLayout.CENTER);
	}
	
	private void setUpTopHud(JPanel top) {
		top.setLayout(new BorderLayout());
		top.setOpaque(false);
		balanceLabel = SwingUtils.createJLabel("V" + EventBus.player.getCurrentMoney(), 44, Color.decode("#FFD72F"), GlobalConstants.VCR_MONO);
		balanceLabel.setBorder(BorderFactory.createEmptyBorder(38, 35, 0, 0));
		top.add(balanceLabel, BorderLayout.WEST);
	}
	
	private void setUpLeftHud(JPanel left) {
		left.setLayout(new BorderLayout());
		left.setOpaque(false);
		left.add(new PageTurner(), BorderLayout.SOUTH);
		left.setBorder(BorderFactory.createEmptyBorder(0, 0, 21, 0));
		
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
		
		viewDeckButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().layerOnScreen(new ViewDeckScreen(), JLayeredPane.MODAL_LAYER);
			}
			
		});
	}
	
	private void setUpCenterHud(JPanel center) {
		center.setOpaque(false);
		center.setLayout(new BorderLayout());
		setStagePage(0);
	}
	
	protected void setStagePage(int index) {
		if (currentPage != null) {
			centerHud.remove(currentPage);
		}
		currentPage = pages[index];
		centerHud.add(currentPage);
		
		centerHud.repaint();
		centerHud.revalidate();
		
	}
	
	public static StageSelectScreen getInstance() {
		return STAGE_SELECT;
	}
	
	
	@Override
	public void addNotify() {
		try {
			SoundBus.playSong("waiting.wav", true);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
		balanceLabel.setText("V" + EventBus.player.getCurrentMoney());
		super.addNotify();
	}
}
