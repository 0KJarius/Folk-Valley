package menuPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class OverlayMenu extends JPanel implements MouseListener{
	
	public OverlayMenu() {
		this.addMouseListener(this);
	}
	
	public OverlayMenu(Color bgColor) {
		this.setBackground(bgColor);
		this.addMouseListener(this);
	}
	
	public OverlayMenu(Dimension size, Color bgColor) {
		this.setPreferredSize(size);
		this.setBackground(bgColor);
		this.addMouseListener(this);
	}
	
	public OverlayMenu(Dimension size, Point location, Color bgColor) {
		this.setBounds(new Rectangle(location, size));
		this.setBackground(bgColor);
		this.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		e.consume();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		e.consume();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		e.consume();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		e.consume();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		e.consume();
	}

}
