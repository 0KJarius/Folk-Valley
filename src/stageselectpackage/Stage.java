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
        
        stageFrame = SwingUtils.getImage(STAGE_BUTTON_PATH + stageFrameName);
        completeStageFrame = SwingUtils.createTintedImage(stageFrame, new Color(100, 255, 100, 100));
        
        try {
			this.setFont(SwingUtils.createFont(SwingUtils.RUBIC_MONO, 60));
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
	
	public void unlockStage() {
		this.setIcon(stageFrame);
		this.setText(stageName);
	}
	
	public void completeStage() {
		isComplete = true;
		this.setIcon(completeStageFrame);
		this.setText("");
	}
	
	public Stage copyStage(String stageName, boolean isLocked, JPanel destination) {
		return new Stage(stageName, textColor, stageFrameName, isLocked, destination);
	}
	
	public Stage copyStage() {
		return new Stage(stageName, textColor, stageFrameName, isLocked, destination);
	}
	
	public Stage copyStage(boolean isLocked) {
		return new Stage(stageName, textColor, stageFrameName, isLocked, destination);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!isLocked && !isComplete) {
			MainFrame.getInstance().setScreen(destination);
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
