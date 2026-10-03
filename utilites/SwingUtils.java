package utilites;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public abstract class SwingUtils {
	
    public static JPanel createJPanel(Dimension size, Color bgColor) 
    {
    	JPanel panel = new JPanel();
    	panel.setPreferredSize(size);
    	panel.setBackground(bgColor);
    	return panel;
    }
    
    public static JLabel createJLabel(String text, Font textStyle, int size, Color textColor) 
    {
    	JLabel label = new JLabel();
    	label.setText(text);
    	label.setFont(new Font(textStyle.getFontName(), textStyle.getStyle(), size));
    	label.setForeground(textColor);
    	return label;
    }
    
    public static JLabel createJLabel(String text, Font textStyle, int size,  Color textColor , Color backgroundColor) 
    {
    	JLabel label = createJLabel(text, textStyle, size, textColor);
    	label.setOpaque(true);
    	label.setBackground(backgroundColor);
    	return label;
    }
    
    public static JProgressBar createJProgressBar(int min, int max, boolean isVertical, String text, int defaultValue, Color fillColor, Color backColor, Dimension size) 
    {
    	JProgressBar progressBar = new JProgressBar(min, max);
    	progressBar.setStringPainted(true);
    	progressBar.setString(text);
    	progressBar.setForeground(fillColor);
    	progressBar.setBackground(backColor);
    	progressBar.setPreferredSize(size);
    	progressBar.setValue(defaultValue);
    	if(isVertical) {
    		progressBar.setOrientation(JProgressBar.VERTICAL);
    	}
    	
    	return progressBar;
    }
}
