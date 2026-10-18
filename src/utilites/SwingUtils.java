package utilites;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;

public abstract class SwingUtils {
	public static final String ASSETS_PATH = "../Assets/";
	public static final String FONT_PATH = ""; // INSERT FONT FILE PATH HERE
	
    public static JPanel createJPanel(Dimension size, Color bgColor) 
    {
    	JPanel panel = new JPanel();
    	panel.setPreferredSize(size);
    	panel.setBackground(bgColor);
    	return panel;
    }
    
    public static JPanel createJPanel(Rectangle pane, Color bgColor) {
    	JPanel panel = new JPanel();
    	panel.setBounds(pane);
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
    
    public static JButton createJButton(Dimension size, String text, Color buttonColor) 
    {
    	JButton button = new JButton(text);
    	button.setPreferredSize(size);
    	button.setBackground(buttonColor);
    	
    	return button;
    }
    
    public static JLabel createJLabel(String text, int size, Color textColor){
        JLabel label = new JLabel(text);
        try {
            label.setFont(createFont(FONT_PATH, size));
        } catch (FontFormatException | IOException e) {}
        label.setForeground(textColor);

        return label;
    }

    public static ImageIcon getImage(String filePath, int width, int height)
    {

        ImageIcon icon = new ImageIcon(SwingUtils.class.getResource(ASSETS_PATH + filePath));
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public static ImageIcon getImage(String filePath, int mult){
        ImageIcon icon = new ImageIcon(SwingUtils.class.getResource(ASSETS_PATH + filePath));
        Image image = icon.getImage().getScaledInstance(icon.getIconWidth() * mult, icon.getIconHeight() * mult, Image.SCALE_FAST);
        return new ImageIcon(image);
    }

    public static ImageIcon getImage(String filePath)
    {
        ImageIcon icon = new ImageIcon(SwingUtils.class.getResource(ASSETS_PATH + filePath));
        return icon;
    }

    public static JLabel createImageAt(String filePath, Point location){
        ImageIcon image = getImage(ASSETS_PATH + filePath);
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBounds(location.x, location.y, image.getIconWidth(), image.getIconHeight());
        return imageLabel;
    }

    public static JLabel createImageAt(ImageIcon image, Point location){
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBounds(location.x, location.y, image.getIconWidth(), image.getIconHeight());
        return imageLabel;
    }


    public static JButton createJButton(String normal, String highlight){
        ImageIcon buttonImg = getImage(normal, 2);
        ImageIcon buttonImgHigh = getImage(highlight, 2);

        JButton button = new JButton(buttonImg);
        button.setPreferredSize(new Dimension(buttonImg.getIconWidth(), buttonImg.getIconHeight()));
        button.setMaximumSize(button.getPreferredSize());
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setPressedIcon(buttonImgHigh);

        return button;
    }
    
    public static JToggleButton createJToggleButton(String normal, String toggle){
        ImageIcon buttonImg = getImage(normal, 2);
        ImageIcon buttonImgTog = getImage(toggle, 2);

        JToggleButton toggleButton = new JToggleButton(buttonImg);
        toggleButton.setPreferredSize(new Dimension(buttonImg.getIconWidth(), buttonImg.getIconHeight()));
        toggleButton.setMaximumSize(toggleButton.getPreferredSize());
        toggleButton.setOpaque(false);
        toggleButton.setBorderPainted(false);
        toggleButton.setFocusPainted(false);
        toggleButton.setContentAreaFilled(false);
        toggleButton.setPressedIcon(buttonImgTog);
        toggleButton.setSelectedIcon(buttonImgTog);

        return toggleButton;
    }

    public static Font createFont(String fontPath, float size) throws FontFormatException, IOException
    {
        Font font = Font.createFont(Font.TRUETYPE_FONT, SwingUtils.class.getResourceAsStream(fontPath)).deriveFont(size);
        return font;
    }
}
