package menuPackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class OverlayMenu extends JPanel implements MouseListener{

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
