package stageselectpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mainpackage.MainFrame;
import utilities.EventBus;
import utilities.GlobalConstants;
import utilities.SoundBus;
import utilities.SwingUtils;

public class Stage extends JButton implements MouseListener{
	private final String STAGE_BUTTON_PATH = "stageSelect/stageButton/";
	private String stageName;
	private final ImageIcon LOCKED_ICON = SwingUtils.getImage(STAGE_BUTTON_PATH + "locked.png");
	private ImageIcon stageFrame;
	private ImageIcon completeStageFrame;
	private Color textColor;
	private String stageFrameName;
	private boolean isLocked;
	private boolean isComplete = false;
	private JPanel destination;
	private Stage nextStage;
	private Integer layer = null;
	
	public Stage(String stageName, Color textColor, String stageFrameName, boolean isLocked, JPanel destination) {
		this.setPreferredSize(new Dimension(654, 111));
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
        this.textColor = textColor;
        this.stageFrameName = stageFrameName;
        this.isLocked = isLocked;
        this.destination = destination;
        this.stageName = stageName;
        
        stageFrame = SwingUtils.getImage(STAGE_BUTTON_PATH + stageFrameName);
        completeStageFrame = SwingUtils.createTintedImage(stageFrame, new Color(100, 255, 100, 200));
        
        try {
			this.setFont(SwingUtils.createFont(GlobalConstants.RUBIC_MONO, 60));
		} catch (FontFormatException | IOException e) {}
        
        this.setText(stageName);
        this.setForeground(Color.BLACK);
        
        if (isLocked) {
        	this.setIcon(LOCKED_ICON);
        	this.setText("");
        } else {
        	this.setIcon(stageFrame);
        }
        this.addMouseListener(this);
	}
	
	public Stage(String stageName, Color textColor, String stageFrameName, boolean isLocked, JPanel destination, Integer layer) {
		this(stageName, textColor, stageFrameName, isLocked, destination);
		this.layer = layer;
	}
	
	public Stage(String stageName, Color textColor, String stageFrameName, boolean isLocked, JPanel destination, Integer layer, Stage nextStage) {
		this(stageName, textColor, stageFrameName, isLocked, destination, nextStage);
		this.layer = layer;
	}
	
	public Stage(String stageName, Color textColor, String stageFrameName, boolean isLocked, JPanel destination, Stage nextStage) {
		this(stageName, textColor, stageFrameName, isLocked, destination);
		this.nextStage = nextStage; 
	}
	
	public void unlockStage() {
		this.setIcon(stageFrame);
		this.setText(stageName);
		System.out.println(stageName);
		isLocked = false;
	}
	
	public void completeStage() {
		isComplete = true;
		this.setIcon(completeStageFrame);
		this.setText("");
	}
	
	public Stage copyStage(String stageName, boolean isLocked, JPanel destination, Stage nextStage) {
		return new Stage(stageName, textColor, stageFrameName, isLocked, destination, nextStage);
	}
	
	public Stage copyStage(String stageName, boolean isLocked, JPanel destination, Integer layer, Stage nextStage) {
		return new Stage(stageName, textColor, stageFrameName, isLocked, destination, layer, nextStage);
	}
	
	public Stage copyStage() {
		return new Stage(stageName, textColor, stageFrameName, isLocked, destination, layer);
	}
	
	public Stage copyStage(Stage nextStage) {
		return new Stage(stageName, textColor, stageFrameName, isLocked, destination, nextStage);
	}
	
	public Stage copyStage(boolean isLocked) {
		return new Stage(stageName, textColor, stageFrameName, isLocked, destination);
	}
	
	public Stage copyStage(boolean isLocked, Stage nextStage) {
		return new Stage(stageName, textColor, stageFrameName, isLocked, destination, nextStage);
	}
	
	public Stage getNextStage() {
		return nextStage;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!isLocked && !isComplete) {
			if (layer == null) {
				MainFrame.getInstance().setScreen(destination);
			} else {
				MainFrame.getInstance().layerOnScreen(destination, layer);
			}
			
			EventBus.selectedStage = this;
		} else {
			try {
				SoundBus.playSFX("button/no.wav");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setForeground(textColor);
		
		if (!isLocked && !isComplete) {
			try {
				SoundBus.playSFX("playButtonHover.wav");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {}
		}
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setForeground(Color.BLACK);
		
	}
}
