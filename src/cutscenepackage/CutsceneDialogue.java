package cutscenepackage;
import javax.swing.*;

import entitypackage.enemypackage.Enemy;
import mainpackage.MainFrame;

import java.awt.*;
import java.io.IOException;

import utilities.GlobalConstants;
import utilities.SwingUtils;


public class CutsceneDialogue extends JPanel {
	private JLabel background;
	private JTextArea textBox;
	private JLabel namePlate;
	private JLabel characterPortrait;
	private JLabel nextIcon;
	private int currentLine = 0;
	private Timer typingTimer;
	private String fullDialogue;
	private int charIndex = 0;
	private boolean isTyping = false;
	private JPanel nextScreen;
	private Enemy enemy;
	
	// TEMPORARY LINES, ARRAY LINES, WHERE currentLine IS THE INDEX
	private CutsceneLines[] dialogueLines;
	
	public CutsceneDialogue(Enemy enemy, String backgroundPath,JPanel nextScreen) {
		super();
		this.nextScreen = nextScreen;
		this.setBounds(new Rectangle(1280, 720));
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		background = SwingUtils.createImageAt("cutscene/BackGround/" + backgroundPath, new Point(0, 0));
		this.enemy = enemy;
		dialogueLines = enemy.getLines();
		
		textBox = SwingUtils.creatJTextArea(new Dimension(1228, 291), GlobalConstants.NOTO_LIGHT, 30, 20, Color.WHITE, new Color(19, 19, 19));
		textBox.setBounds(26, 409, 1228, 291);
		textBox.setOpaque(true);

		this.add(background);
		this.add(textBox);
				
		namePlate = SwingUtils.createImageAt(enemy.getNamePlate(), new Point(26, 365));
		this.add(namePlate);
		
		characterPortrait = SwingUtils.createImageAt(enemy.getDialougePortraitPath() + "/talk/normal.gif", new Point(290, 10));
		this.add(characterPortrait);
		
		nextIcon = SwingUtils.createImageAt("cutscene/nextIndicator.png", new Point(1150, 680), 1);
		this.add(nextIcon);
		nextIcon.setVisible(false);
		
		this.setComponentZOrder(nextIcon, 0);
		this.setComponentZOrder(namePlate, 1);
		this.setComponentZOrder(textBox, 2);
		this.setComponentZOrder(characterPortrait, 3);
		this.setComponentZOrder(background, 4);
		showDialogue();
		
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override	
			public void mouseClicked(java.awt.event.MouseEvent e) {
				nextDialogue();
			}
				
		});
		
	}
	
	
	// TYPES THE LETTERS ONE BY ONE
	private void showDialogue() {
		
		nextIcon.setVisible(false);
		
		fullDialogue = dialogueLines[currentLine].getDialogue();
		changePortrait(dialogueLines[currentLine].getExpression(), true);
		charIndex = 0;
		isTyping = true;
		
		textBox.setText("");
		
		typingTimer = new Timer(40, e -> {
			
			// IF NOT CLIKED, IT WILL DISPLAY CHARACTERS, THEN IF FINISHED, TIMER WILL STOP THEN isTyping WILL BECOME FALSE
			
			if (charIndex < fullDialogue.length()) {
				textBox.append(String.valueOf(fullDialogue.charAt(charIndex)));
				charIndex++;

				
			} else {
				typingTimer.stop();
				isTyping = false;
				changePortrait(dialogueLines[currentLine].getExpression(), false);
				nextIcon.setVisible(true);
			}
			
		});
		
		typingTimer.start();
		

	}
	
	// IF CLICKED, RUNS THE FULL TEXT, THEN IS TYPING BECOMES FALSE, THEN CLICK AGAIN FOR THE NEXT LINE, IF ITS ALREADY FALSE THEN NEXT LINE
	private void nextDialogue() {
		if(isTyping) {
			typingTimer.stop();
			textBox.setText(fullDialogue);
			isTyping = false;
			changePortrait(dialogueLines[currentLine].getExpression(), false);
			nextIcon.setVisible(true);
		} else {
			currentLine++;
		
			if (currentLine < dialogueLines.length) {
				showDialogue();
			} else {
				MainFrame.FRAME.setScreen(nextScreen);
			}
		}
		
	}

	//WILL BASE ON THE isTyping, IF TRUE THEN TALK ANG GIF, IF FALSE THEN IDLE AND PNG
	private void changePortrait(String expression, boolean talking) {
		String state = talking ? "talk" : "idle";
		String extension = talking? ".gif" : ".png";

		ImageIcon icon = SwingUtils.getImage(enemy.getDialougePortraitPath() + state + "/" + expression + extension);
		characterPortrait.setIcon(icon);
	}
	



}
