package menuPackage.startmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entitypackage.Player;
import mainpackage.MainFrame;
import menuPackage.OverlayMenu;
import stageselectpackage.StageCollection;
import stageselectpackage.StageSelectScreen;
import utilities.EventBus;
import utilities.GlobalConstants;
import utilities.SwingUtils;

public class NewGameMenu extends OverlayMenu{
	private final String BUTTON_PATH = "LoadNew/newGame/";
	JTextField nameField = new JTextField();
	
	private JButton newGame = SwingUtils.createJButton(
			BUTTON_PATH + "normal.png", 
			BUTTON_PATH + "highlight.png", 
			BUTTON_PATH + "pressed.png", 
			BUTTON_PATH + "disabled.png");
	
	public NewGameMenu() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBounds(new Rectangle(GlobalConstants.SCREEN_SIZE));
		JLayeredPane basePane = new JLayeredPane();
		basePane.setPreferredSize(GlobalConstants.SCREEN_SIZE);
		this.add(basePane);
		JPanel contentPanel = SwingUtils.createJPanel(new Rectangle(402, 135, 475, 438), getBackground());
		setUpContentPanel(contentPanel);
		
		basePane.add(contentPanel, JLayeredPane.PALETTE_LAYER);
		basePane.add(SwingUtils.createImageAt("LoadNew/newSaveScreen.png", new Point(0, 0)), JLayeredPane.DEFAULT_LAYER);
		newGame.setEnabled(false);
		this.addMouseListener(this);
		
		newGame.addActionListener(e -> onConfirmPressed());
	}
	
	private void setUpContentPanel(JPanel content) {
		content.setOpaque(false);
		content.setLayout(new BorderLayout());
		newGame.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		JPanel center = new JPanel();
		setUpCenter(center);
		
		
		
		content.add(newGame, BorderLayout.SOUTH);
		content.add(Box.createRigidArea(new Dimension(0, 95)), BorderLayout.NORTH);
		content.add(center, BorderLayout.CENTER);
	}
	
	private void setUpCenter(JPanel center) {
		center.setOpaque(false);
		center.setLayout(new BorderLayout());
		
		nameField.setPreferredSize(new Dimension(0, 86));
		nameField.setForeground(Color.white);
		nameField.setOpaque(false);
		nameField.setCaretColor(new Color(200, 200, 200));
		nameField.setBorder(null);
		try {
			nameField.setFont(SwingUtils.createFont(GlobalConstants.VCR_MONO, 70));
		} catch (FontFormatException | IOException e) {
		}
		nameField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				newGame.setEnabled(true);	
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if (nameField.getText().length() == 0) {
					newGame.setEnabled(false);
				}
				
			}
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
		
		center.add(nameField, BorderLayout.NORTH);
	}
	
	private void onConfirmPressed() {
		EventBus.player = new Player(100, nameField.getText());
		StageCollection.onNewGame();
		MainFrame.getInstance().removeLayer(this);
		MainFrame.getInstance().setScreen(StageSelectScreen.getInstance());
	}
}
