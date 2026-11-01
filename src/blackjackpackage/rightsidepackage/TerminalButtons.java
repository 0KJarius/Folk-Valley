package blackjackpackage.rightsidepackage;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import utilities.SwingUtils;

public class TerminalButtons extends JPanel{
	private final String BUTTON_PATH = "BlackJack/terminal/terminalButtons/";
	private final String PREV = BUTTON_PATH + "previous/";
	private final String INFO = BUTTON_PATH + "info/";
	private final String NEXT = BUTTON_PATH + "next/";
	private final String USE = BUTTON_PATH + "use/";
	private final String[] paths = {PREV, INFO, NEXT, USE};
	
	private JButton prevButton, infoButton, nextButton, useButton;
	private  final JButton[] buttons = {prevButton, infoButton, nextButton, useButton};
	
	public TerminalButtons() {
		this.setPreferredSize(new Dimension(201, 155));
		this.setOpaque(false);
		createTerminalButtons();
	}
	
	private void createTerminalButtons() {
		for (int i = 0; i < paths.length; i++) {
			buttons[i] = SwingUtils.createJButton(
					paths[i] + "normal.png", 
					paths[i] + "highlight.png", 
					paths[i] + "pressed.png", 
					paths[i] + "disabled.png"
			);
			this.add(buttons[i]);
		}
	}
	
}
