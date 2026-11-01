package blackjackpackage.rightsidepackage;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import utilities.EventBus;
import utilities.SwingUtils;

public class TerminalButtons extends JPanel{
	private final String BUTTON_PATH = "BlackJack/terminal/terminalButtons/";
	private final String PREV = BUTTON_PATH + "previous/";
	private final String INFO = BUTTON_PATH + "info/";
	private final String NEXT = BUTTON_PATH + "next/";
	private final String USE = BUTTON_PATH + "use/";
	private final String[] paths = {PREV, INFO, NEXT, USE};
	private int itemIndex = 0;
	private BottomInfoArea terminal;

	private  final JButton[] buttons = new JButton[4];
	
	public TerminalButtons(BottomInfoArea bottom) {
		terminal = bottom;
		this.setPreferredSize(new Dimension(201, 155));
		this.setOpaque(false);
		createTerminalButtons();
		
		buttons[0].addActionListener(e -> onPrevButtonPressed());
		buttons[2].addActionListener(e -> onNextButtonPressed());
		buttons[3].addActionListener(e -> onUseButtonPressed());
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
	
	private void onNextButtonPressed() {
		if (EventBus.player.inventory.isEmpty() || itemIndex == EventBus.player.inventory.size() - 1) {
			return;
		}
		
		
		terminal.itemSelect.remove(EventBus.player.inventory.get(itemIndex));
		itemIndex++;
		terminal.itemSelect.add(EventBus.player.inventory.get(itemIndex));
		terminal.currentItemLabel.setText(EventBus.player.inventory.get(itemIndex).getName());
		terminal.repaint();
		terminal.revalidate();
		System.out.println("Change NEXT");
	}
	
	private void onPrevButtonPressed() {
		if (EventBus.player.inventory.isEmpty() || itemIndex == 0) {
			return;
		}
		
		terminal.itemSelect.remove(EventBus.player.inventory.get(itemIndex));
		itemIndex--;
		terminal.itemSelect.add(EventBus.player.inventory.get(itemIndex));
		terminal.currentItemLabel.setText(EventBus.player.inventory.get(itemIndex).getName());
		terminal.repaint();
		terminal.revalidate();
		System.out.println("Change PREV");
	}
	
	private void onUseButtonPressed() {
		if (EventBus.player.inventory.isEmpty()) {
			return;
		}
		
		EventBus.player.inventory.get(itemIndex).doTask();
		System.out.println("USE");
	}
}
