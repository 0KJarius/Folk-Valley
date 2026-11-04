package blackjackpackage.rightsidepackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import utilities.GlobalConstants;
import utilities.SwingUtils;

public class ItemInfoScreen extends JPanel{
	protected JTextArea infoText = SwingUtils.creatJTextArea(new Dimension(164, 150), GlobalConstants.VCR_MONO, 15, 2, Color.WHITE, getBackground());
	
	public ItemInfoScreen() {
		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
		this.setPreferredSize(new Dimension(201, 150));
		layout.setVgap(0);
		infoText.setOpaque(false);
		this.add(infoText);
	}
}
