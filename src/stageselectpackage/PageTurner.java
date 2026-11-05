package stageselectpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import utilities.GlobalConstants;
import utilities.SwingUtils;

public class PageTurner extends JPanel{
	private final String PAGE_BUTTON = "stageSelect/pageTurn/";
	private int currentPage = 1;
	private JButton prevButton = SwingUtils.createJButton(PAGE_BUTTON + "prev/normal.png", PAGE_BUTTON + "prev/pressed.png");
	private JButton nextButton = SwingUtils.createJButton(PAGE_BUTTON + "next/normal.png", PAGE_BUTTON + "next/pressed.png");
	private JLabel pageCounter = SwingUtils.createJLabel(currentPage + "/" + StageCollection.getPageCount(), 65, Color.WHITE, GlobalConstants.VCR_MONO);
	
	public PageTurner() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(0, 280));
		this.setOpaque(false);
		JLayeredPane basePane = new JLayeredPane();
		basePane.setPreferredSize(getPreferredSize());
		this.add(basePane);
		
		JPanel content = SwingUtils.createJPanel(new Rectangle(275, 280), getBackground());
		setUpContent(content);
		
		basePane.add(SwingUtils.createImageAt("stageSelect/pageTerminal.png", new Point(0, 0)), JLayeredPane.DEFAULT_LAYER);
		basePane.add(content, JLayeredPane.PALETTE_LAYER);
		setUpButtons();
	}
	
	private void setUpContent(JPanel content) {
		content.setLayout(new BorderLayout());
		content.setOpaque(false);
		
		JPanel buttonsPanel = SwingUtils.createJPanel(new Dimension(256, 170), Color.BLACK);
		buttonsPanel.setOpaque(false);
		buttonsPanel.add(prevButton);
		buttonsPanel.add(nextButton);
		pageCounter.setBorder(BorderFactory.createEmptyBorder(10, 75, 0, 0));
		content.add(pageCounter, BorderLayout.NORTH);
		content.add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	private void setUpButtons() {
		prevButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentPage == 1) {
					return;
				}
				currentPage--;
				pageCounter.setText(currentPage + "/" + StageCollection.getPageCount());
				StageSelectScreen.getInstance().setStagePage(currentPage - 1);
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentPage == StageCollection.getInstance().getPageCount()) {
					return;
				}
				currentPage++;
				pageCounter.setText(currentPage + "/" + StageCollection.getPageCount());
				StageSelectScreen.getInstance().setStagePage(currentPage - 1);
			}
		});
	}
}
