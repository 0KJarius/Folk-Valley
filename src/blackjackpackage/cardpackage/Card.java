package blackjackpackage.cardpackage;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JToggleButton;

import utilities.AnimationUtils;
import utilities.SoundBus;

public abstract class Card extends JToggleButton 
{
    private int score;
    private Random rng;

    public Card()
    {
        rng = new Random();
        score = rng.nextInt(1, 11);
        this.setPreferredSize(new Dimension(150, 200));
        this.repaint();
    }
    
    public Card(int score) {
    	this.score = score; 
    	this.setText("" + this.score);
        this.setPreferredSize(new Dimension(150, 200));
    }
    
    public int getCardScore()
    {
        return score;
    }

    public void removeThis(){
        this.setVisible(false);
        this.remove(this);
    }

    public void flipCard(){
        try {
			SoundBus.playSFX("card/flipCard.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
    }

    public void unflipCard(){
    	try {
			SoundBus.playSFX("card/flipCard.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
    }
    
    public void setCardScore(int setScore) {
    	this.score = setScore;
    }
    
    @Override
    public void addNotify() {
    	this.setVisible(true);
    	AnimationUtils.popup(this, 4.5, 500, 19);
    	try {
			SoundBus.playSFX("card/cardSlide.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
    	super.addNotify();
    }
}
