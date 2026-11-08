package cutscenepackage;
import javax.swing.*;

import mainpackage.MainFrame;

import java.awt.*;
import java.io.IOException;

import utilities.SwingUtils;


public class cutsceneDialogue extends JPanel {
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
	
	// TEMPORARY LINES, ARRAY LINES, WHERE currentLine IS THE INDEX
	private cutsceneLines[] dialogueLines = {
			new cutsceneLines("", "just add some lines later", "normal"),
			new cutsceneLines("Protosuer", "Hi! It's me, Protosuer!", "normal"),
			new cutsceneLines("Protosuer", "Do you want to die?", "angry"),
			new cutsceneLines("Protosuer", "Sliming you, Sliming me.", "sad")
				
	};
	
	
	public cutsceneDialogue(JPanel nextScreen) {
		super();
		this.nextScreen = nextScreen;
		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		System.out.println("Cutscene constructor started!");
		background = SwingUtils.createImageAt("cutscene/BackGround/placeValley.png", new Point(0, 0), 1);

		
		textBox = SwingUtils.creatJTextArea(new Dimension(1228, 291), "MartianMono-VariableFont_wdth,wght.ttf", 180, 20, Color.WHITE, new Color(0, 0, 0, 180));
//for some reason the text font on textbox won't work so i tried this
		try {
		    textBox.setFont(SwingUtils.createFont(
		            "/Assets/fonts/MartianMono-VariableFont_wdth,wght.ttf",
		            32f
		        ));
		} catch (FontFormatException | IOException e) {
		    e.printStackTrace();
		}
		
		textBox.setBounds(26, 409, 1228, 291);
		textBox.setOpaque(true);
		textBox.setBackground(new Color(35, 35, 35));


		this.add(background);
		this.add(textBox);
		


		
		namePlate = SwingUtils.createImageAt("cutscene/namePlates/protosuer.png", new Point(26, 365), 1);
		
		this.add(namePlate);


		ImageIcon portraitIcon = new ImageIcon(
			    SwingUtils.class.getResource("../Assets/cutscene/Portraits/Protosuer/talk/normal.gif")
			);

			characterPortrait = new JLabel(portraitIcon);
			characterPortrait.setBounds(290, 10, 700, 400);

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
		String imagePath = "../Assets/cutscene/Portraits/Protosuer/" + state + "/" + expression + extension;
		System.out.println(imagePath);
		System.out.println(SwingUtils.class.getResource(imagePath));
		ImageIcon icon = new ImageIcon(
				SwingUtils.class.getResource(imagePath)
				
				);
		characterPortrait.setIcon(icon);
	}
	



}
