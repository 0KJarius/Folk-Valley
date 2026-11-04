package menuPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.GlobalConstants;
import utilities.Signal;
import utilities.SwingUtils;

public class JarButton extends JPanel implements MouseListener{
	private final String TYPE_ONE = "UIButtons/typeOne/";
	private final String TYPE_TWO = "UIButtons/typeTwo/";
	private final Dimension TYPE_ONE_SIZE = new Dimension(400, 102);
	private final Dimension TYPE_TWO_SIZE = new Dimension(356, 102);
	private Dimension size;
	private String type;
	private JLabel buttonImg;
	private JLabel buttonText;
	private Signal task;
	
	public JarButton(boolean isTypeOne, String text, int textSize, Signal task) 
	{
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.task = task;
		
		if (isTypeOne) {
			type = TYPE_ONE;
			size = TYPE_ONE_SIZE;
		} else {
			type = TYPE_TWO;
			size = TYPE_TWO_SIZE;
		}
		
		this.setPreferredSize(size);
		this.setBounds(new Rectangle(size));
		
		JLayeredPane basePane = new JLayeredPane();
		basePane.setPreferredSize(new Dimension(size));
		this.add(basePane);
		
		buttonImg = SwingUtils.createImageAt(type + "normal.png", new Point(0, 0));
		buttonText = SwingUtils.createJLabel(text, textSize, Color.BLACK, GlobalConstants.NIKKYOU_SANS);
		JPanel textContainer = SwingUtils.createJPanel(new Rectangle(size), getBackground());
		setUpTextContainer(textContainer);
		
		basePane.add(buttonImg, JLayeredPane.DEFAULT_LAYER);
		basePane.add(textContainer, JLayeredPane.PALETTE_LAYER);
		
		this.addMouseListener(this);
	}
	
	private void setUpTextContainer(JPanel container) {
		container.setOpaque(false);
		container.add(buttonText);
		
		buttonText.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		buttonText.setVerticalTextPosition(SwingConstants.CENTER);
	}
	
	public JarButton(Point location, boolean isTypeOne, String text, int textSize, Signal task) 
	{
		this(isTypeOne, text , textSize, task);
		this.setLocation(location);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttonImg.setIcon(SwingUtils.getImage(type + "pressed.png"));
		buttonText.setLocation(buttonText.getX() - 5, buttonText.getY() + 10);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttonImg.setIcon(SwingUtils.getImage(type + "hover.png"));
		buttonText.setLocation(buttonText.getX() + 5, buttonText.getY() - 10);
		task.doTask();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		buttonImg.setIcon(SwingUtils.getImage(type + "hover.png"));
		buttonText.setForeground(Color.WHITE);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		buttonImg.setIcon(SwingUtils.getImage(type + "normal.png"));
		buttonText.setForeground(Color.BLACK);
	}
}
