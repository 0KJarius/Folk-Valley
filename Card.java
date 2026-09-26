import java.awt.Dimension;
import java.util.Random;

import javax.swing.JButton;

public class Card extends JButton 
{
    private int score;
    private Random rng;

    public Card()
    {
        rng = new Random();
        score = rng.nextInt(1, 11);
        this.setText("" + score);
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

    public void hideCard(){
        this.setVisible(false);
        this.remove(this);
    }

    public void hideCardScore(){
        this.setText("");
    }

    public void showCardScore(){
        this.setText("" + score);
    }
    
}
