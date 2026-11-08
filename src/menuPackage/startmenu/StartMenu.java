package menuPackage.startmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import mainpackage.MainFrame;
import stageselectpackage.StageSelectScreen;
import utilities.SoundBus;
import utilities.SwingUtils;

public class StartMenu extends JPanel{
	private final String PLAYBUTTON = "mainmenu/playButton.png";
	private final String PLAYBUTTON_HIGHLIGHT = "mainmenu/playButton_highlight.png";
	
	public StartMenu() 
	{
		this.setBounds(new Rectangle(1280, 720));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GREEN);
		
		JLayeredPane basePane = new JLayeredPane();
		
		basePane.add(SwingUtils.createImageAt("mainmenu/startmenuAnim.gif", new Point(0,0)));
		this.add(basePane);
		JLabel playButton = SwingUtils.createImageAt(PLAYBUTTON, new Point(0, 488));
		setUpPlayButton(playButton);
		
		basePane.add(playButton, JLayeredPane.PALETTE_LAYER);
		try {
			SoundBus.playSong("mainMenuSong.wav", false);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
	}
	
	private void setUpPlayButton(JLabel playButton) 
	{
		playButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				playButton.removeMouseListener(this);
				try {
					SoundBus.playSFX("playButtonSelect.wav");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {}
				try(FileReader reader = new FileReader("Save.json")){
					MainFrame.getInstance().layerOnScreen(new LoadGameMenu(), JLayeredPane.DRAG_LAYER);
					
				} catch (FileNotFoundException e1) {
					MainFrame.getInstance().layerOnScreen(new NewGameMenu(), JLayeredPane.DRAG_LAYER);
				} catch (IOException e1) {}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {
				playButton.setIcon(SwingUtils.getImage(PLAYBUTTON_HIGHLIGHT));
				
				try {
					SoundBus.playSFX("playButtonHover.wav");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {}
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				playButton.setIcon(SwingUtils.getImage(PLAYBUTTON));
			}
			
		});
	}
	
	
}
