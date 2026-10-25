package blackjackpackage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedHashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import utilities.EventBus;
import utilities.SwingUtils;

public class BetTable extends JPanel
{
	private final String BET_TABLE_PATH = "BlackJack/betTable/";
	private final String CONFIRM_PATH = "BlackJack/confirmButton/";
	private final String CHIP_BUTTON_PATH = BET_TABLE_PATH + "chipButtons/";
	private final String ONE = CHIP_BUTTON_PATH + "1/";
	private final String FIVE = CHIP_BUTTON_PATH + "5/";
	private final String TEN = CHIP_BUTTON_PATH + "10/";
	private final String TWENTY = CHIP_BUTTON_PATH + "20/";
	private final String HUNDRED = CHIP_BUTTON_PATH + "100/";
	private final String FIVE_HUNDO = CHIP_BUTTON_PATH + "500/";
	private final String ALL_IN = CHIP_BUTTON_PATH + "allIn/";
	
	private LinkedHashMap<JButton, Integer> betChips = new LinkedHashMap<JButton, Integer>() {{
		put(SwingUtils.createJButton(ONE + "1.png", ONE + "1_pressed.png"), 1);
		put(SwingUtils.createJButton(FIVE + "5.png", FIVE + "5_pressed.png"), 5);
		put(SwingUtils.createJButton(TEN + "10.png", TEN + "10_pressed.png"), 10);
		put(SwingUtils.createJButton(TWENTY + "20.png", TWENTY + "20_pressed.png"), 20);
		put(SwingUtils.createJButton(HUNDRED + "100.png", HUNDRED + "100_pressed.png"), 100);
		put(SwingUtils.createJButton(FIVE_HUNDO + "500.png", FIVE_HUNDO + "500_pressed.png"), 500);
		
	}};
	
    public BetTable()
    {
    	this.setLayout(new BorderLayout());
    	this.setBounds(new Rectangle(255, 0, 819, 720));
        this.setBackground(Color.ORANGE);
        
        JLayeredPane basePane = new JLayeredPane();
        basePane.setPreferredSize(new Dimension(819, 720));
        this.add(basePane);
        
        JPanel contentPanel = SwingUtils.createJPanel(new Rectangle(819, 720), getBackground());
        setUpContentPanel(contentPanel);
        
        basePane.add(contentPanel, JLayeredPane.PALETTE_LAYER);
        basePane.add(SwingUtils.createImageAt(BET_TABLE_PATH + "betTable.png", new Point(0, 0)), JLayeredPane.DEFAULT_LAYER);
    }
    
    private void setUpContentPanel(JPanel contentPanel) 
    {
    	contentPanel.setOpaque(false);
    	contentPanel.setLayout(new BorderLayout());
    	
    	JPanel body = SwingUtils.createJPanel(new Dimension(819, 619), getBackground());
    	setUpBody(body);
    	
    	JPanel footer = SwingUtils.createJPanel(new Dimension(819, 103), getBackground());
    	footer.setOpaque(false);
    	
    	JButton confirmButton = SwingUtils.createJButton(CONFIRM_PATH + "confirm.png", CONFIRM_PATH + "confirm_highlight.png", CONFIRM_PATH + "confirm_pressed.png", CONFIRM_PATH + "confirm_disable.png");
    	setUpConfirmButton(confirmButton);
        
        footer.add(confirmButton);
        
        contentPanel.add(footer, BorderLayout.SOUTH);
        contentPanel.add(body);
    }
    
    private void setUpBody(JPanel body) {
    	body.setOpaque(false);
    	body.setBorder(BorderFactory.createEmptyBorder(29, 29, 0, 29));
    	for (JButton chip : betChips.keySet()) {
    		setUpBetChip(chip, betChips.get(chip));
    		body.add(chip);
    	}
    	
    	//miraculously, these boxes keep everything in format
    	body.add(Box.createRigidArea(new Dimension(450, 0)));
    	body.add(Box.createRigidArea(new Dimension(60, 0)));
    	body.add(Box.createRigidArea(new Dimension(40, 0)));
    	body.add(Box.createRigidArea(new Dimension(40, 0)));
    	body.add(Box.createRigidArea(new Dimension(60, 0)));
    	
    	JButton allIn = SwingUtils.createJButton(ALL_IN + "allIn.png", ALL_IN + "allIn_pressed.png");
    	setUpAllInButton(allIn);
    	body.add(allIn);
    	
    }
    
    private void setUpAllInButton(JButton allIn) {
    	allIn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (EventBus.player.getCurrentMoney() >= EventBus.player.getCurrentMoney()) {
					EventBus.currentMatch.addBetPool(EventBus.player.getCurrentMoney());
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {
				allIn.setLocation(allIn.getX(), allIn.getY() - 10);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				allIn.setLocation(allIn.getX(), allIn.getY() + 10);
			}
    		
    	});
    }
    
    private void setUpConfirmButton(JButton confirmButton) {
    	confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	confirmBet();
            }
        });
    }
    
    private void setUpBetChip(JButton chip, int betAmount) {
    	chip.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (EventBus.player.getCurrentMoney() >= betAmount) {
					EventBus.currentMatch.addBetPool(betAmount);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {
				chip.setLocation(chip.getX(), chip.getY() - 10);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				chip.setLocation(chip.getX(), chip.getY() + 10);
			}
    		
    	});
    }
    
    private void confirmBet() {
    	if (EventBus.currentMatch.betPool == 0){
            return;
        }
        EventBus.frame.removeLayer(this);
    }
    
}
