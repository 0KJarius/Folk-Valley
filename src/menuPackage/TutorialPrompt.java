package menuPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import utilities.GlobalConstants;
import utilities.Signal;
import utilities.SwingUtils;

public class TutorialPrompt extends JPanel{
	private final Color BLOCKER_COLOR = new Color(0, 0, 0, 200);
	private OverlayMenu blockerTop = new OverlayMenu(BLOCKER_COLOR);
	private OverlayMenu blockerBot = new OverlayMenu(BLOCKER_COLOR);
	private OverlayMenu blockerLeft = new OverlayMenu(BLOCKER_COLOR);
	private OverlayMenu blockerRight = new OverlayMenu(BLOCKER_COLOR);
	public JPanel activeBlock = SwingUtils.createJPanel(new Dimension(0,0), BLOCKER_COLOR);
	private JPanel content;
	private JTextArea tutorialText = SwingUtils.creatJTextArea(new Dimension(0, 0), GlobalConstants.MARTIAN_MONO, 25, 10, Color.WHITE, new Color(122, 26, 22));
	private JLayeredPane basePane;
	private Signal task;
	
	
	

	public TutorialPrompt() {
		this.setBounds(0, 0, 1280, 720);
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		basePane = new JLayeredPane();
		basePane.setPreferredSize(new Dimension(1280, 720));
		this.add(basePane);
		
		content = SwingUtils.createJPanel(new Rectangle(1280, 720), BLOCKER_COLOR);
		content.setLayout(new BorderLayout());
		content.setOpaque(false);
		basePane.add(content, JLayeredPane.DEFAULT_LAYER);
		
		tutorialText.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(205, 3, 52), 5), tutorialText.getBorder()));
		basePane.add(tutorialText, JLayeredPane.PALETTE_LAYER);
		activeBlock.setOpaque(false);
		
		content.add(blockerTop, BorderLayout.NORTH);
		content.add(blockerBot, BorderLayout.SOUTH);
		content.add(blockerLeft, BorderLayout.WEST);
		content.add(blockerRight, BorderLayout.EAST);
		content.add(activeBlock, BorderLayout.CENTER);
		
		setUpActiveBlock();
	}
	
	public void setBlockers(int topHeight, int botHeight, int leftWidth, int rightWidth) {
		blockerTop.setPreferredSize(new Dimension(0, topHeight));
		blockerBot.setPreferredSize(new Dimension(0, botHeight));
		blockerLeft.setPreferredSize(new Dimension(leftWidth, 0));
		blockerRight.setPreferredSize(new Dimension(rightWidth, 0));
		
		content.repaint();
		content.revalidate();
	}
	
	public void setTutorialText(String text) {
		tutorialText.setText(text);
	}
	
	public void setTutorialText(String text, Point location) {
		tutorialText.setText(text);
		tutorialText.setLocation(location);
		
		this.repaint();
	}
	
	public void setTutorialText(String text, Dimension size) {
		tutorialText.setText(text);
		tutorialText.setPreferredSize(size);
		tutorialText.setSize(size);
	}
	
	public void setTutorialText(String text, Dimension size, Point location) {
		setTutorialText(text, size);
		tutorialText.setLocation(location);
	}
	
	public void setTask(Signal task) {
		this.task = task;
	}
	
	private void setUpActiveBlock() {
		activeBlock.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (task == null) {
					return;
				}
				task.doTask();
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
	}
	
}
