package utilities;
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
import javax.swing.plaf.basic.BasicProgressBarUI;

public abstract class SwingUtils {
	public static final String ASSETS_PATH = "../Assets/";
	public static final String FONT_PATH = "../Assets/fonts/";
	public static final String MARTIAN_MONO = FONT_PATH + "MartianMono-VariableFont_wdth,wght.ttf";
	public static final String HONK = FONT_PATH + "Honk-Regular-VariableFont_MORF,SHLN.ttf";
	public static final String VCR_MONO = FONT_PATH + "VCR_OSD_MONO_1.001.ttf";
	public static final String RUBIC_MONO = FONT_PATH + "RubikMonoOne-Regular.ttf";
	public static final String NIKKYOU_SANS = FONT_PATH + "NikkyouSans-B6aV.ttf";
	public static final String M6XPLUS = FONT_PATH + "m6x11plus.ttf";
	
	
    public static JPanel createJPanel(Dimension size, Color bgColor) 
    {
    	JPanel panel = new JPanel();
    	panel.setPreferredSize(size);
    	panel.setBackground(bgColor);
    	return panel;
    }
    
    public static JPanel createJPanel(Rectangle bounds, Color bgColor) {
    	JPanel panel = new JPanel();
    	panel.setBounds(bounds);
    	panel.setBackground(bgColor);
    	return panel;
    }
    
    public static JLabel createJLabel(String text, int size, Color textColor, String font){
        JLabel label = new JLabel(text) {
        	//ggraohic s2d
        	
        	
        	@Override 
        	protected void paintComponent(java.awt.Graphics g) {
        		   Double scaleObj = (Double) getClientProperty(AnimationUtils.KEY_SCALE);
        	        Double angleObj = (Double) getClientProperty(AnimationUtils.KEY_ANGLE);
        	        
        	        double scale = (scaleObj != null) ? scaleObj : 1.0;
        	        double angle = (angleObj != null) ? angleObj : 0.0;

        	        java.awt.Graphics2D g2d = (java.awt.Graphics2D) g.create();
        	        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        	        
        	        
        	        
        	        int cx = getWidth() / 2;
        	        int cy = getHeight() / 2;
        	        g2d.translate(cx, cy);
        	        g2d.scale(scale, scale);
        	        g2d.rotate(angle);
        	        g2d.translate(-cx, -cy);
        	        
        	        super.paintComponent(g2d);
        	        g2d.dispose();
        	}
        	
        	@Override
        	protected void paintBorder(java.awt.Graphics g) {
        		
        		
        		
        		  Double scaleObj = (Double) getClientProperty(AnimationUtils.KEY_SCALE);
        	        Double angleObj = (Double) getClientProperty(AnimationUtils.KEY_ANGLE);
        	        double scale = (scaleObj != null) ? scaleObj : 1.0;
        	        double angle = (angleObj != null) ? angleObj : 0.0;

        	        java.awt.Graphics2D g2d = (java.awt.Graphics2D) g.create();
        	        int cx = getWidth() / 2; int cy = getHeight() / 2;
        	        g2d.translate(cx, cy); g2d.scale(scale, scale); g2d.rotate(angle); g2d.translate(-cx, -cy);
        	        super.paintBorder(g2d);
        	        g2d.dispose();
        	}
        };
        try {
            label.setFont(createFont(font, size));
        } catch (FontFormatException | IOException e) {}
        label.setForeground(textColor);

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
    	progressBar.setBorderPainted(false);
    	
    	progressBar.setUI(new BasicProgressBarUI() {
    		protected Color getSelectionBackground() { return Color.WHITE; }
    	    protected Color getSelectionForeground() { return Color.BLACK; } 
    	});
    	
    	try {
			progressBar.setFont(createFont(RUBIC_MONO, 30));
		} catch (FontFormatException | IOException e) {}
    	if(isVertical) {
    		progressBar.setOrientation(JProgressBar.VERTICAL);
    	}
    	
    	return progressBar;
    }
    
    public static ImageIcon getImage(String filePath)
    {
        ImageIcon icon = new ImageIcon(SwingUtils.class.getResource(ASSETS_PATH + filePath));
        return icon;
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


    public static JLabel createImageAt(String filePath, Point location){
        ImageIcon image = getImage(ASSETS_PATH + filePath);
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBounds(location.x, location.y, image.getIconWidth(), image.getIconHeight());
        return imageLabel;
    }

    public static JLabel createImageAt(String filePath, Point location, int mult){
    	ImageIcon image = getImage(ASSETS_PATH + filePath, mult);
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBounds(location.x, location.y, image.getIconWidth(), image.getIconHeight());
        return imageLabel;
    }
    
    public static JButton createJButton(Dimension size, String text, Color buttonColor) 
    {
    	JButton button = new JButton(text);
    	button.setPreferredSize(size);
    	button.setBackground(buttonColor);
    	
    	return button;
    }


    public static JButton createJButton(String normal, String pressed){
        ImageIcon buttonImg = getImage(normal);
        ImageIcon buttonImgPress = getImage(pressed);

        JButton button = new JButton(buttonImg);
        button.setPreferredSize(new Dimension(buttonImg.getIconWidth(), buttonImg.getIconHeight()));
        button.setMaximumSize(button.getPreferredSize());
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setPressedIcon(buttonImgPress);

        return button;
    }
    
    public static JButton createJButton(String normal, String highlight, String pressed, String disable) {
    	JButton button = createJButton(normal, pressed);
    	ImageIcon buttonImgHigh = getImage(highlight);
        ImageIcon buttonImgDisable = getImage(disable);
    	
    	button.setRolloverEnabled(true);
    	button.setRolloverIcon(buttonImgHigh);
    	button.setDisabledIcon(buttonImgDisable);
    	
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
