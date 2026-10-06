package utilites;

import javax.swing.JPanel;

import blackjackpackage.BlackJackScreen;
import entitypackage.Entity;
import entitypackage.Player;
import menuPackage.GameOverScreen;

public class EventBus {
	private Player player;
	private Entity enemy;
	private BlackJackScreen currentMatch;
	private MainFrame frame;
	private GameOverScreen gameOverScreen;
	
	public EventBus(MainFrame frame) 
	{
		this.frame = frame;
	}
	
	
	public void hardResetMatch() 
	{
		player.resetWallet();
		enemy.resetWallet();
		currentMatch.reset();
	}
	
	public void removeLayerFromFrame(JPanel panel) 
	{
		frame.removeLayer(panel);	
	}
	
	public void addLayerToFrame(JPanel panel, Integer layer){
		frame.layerOnScreen(panel, layer);
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Entity getEnemy() {
		return enemy;
	}
	public void setEnemy(Entity enemy) {
		this.enemy = enemy;
	}
	public BlackJackScreen getCurrentMatch() {
		return currentMatch;
	}
	public void setCurrentMatch(BlackJackScreen currentMatch) {
		this.currentMatch = currentMatch;
	}
	public MainFrame getFrame() {
		return frame;
	}
	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}
	public GameOverScreen getGameOverScreen() {
		return gameOverScreen;
	}
	public void setGameOverScreen(GameOverScreen gameOverScreen) {
		this.gameOverScreen = gameOverScreen;
	}
	
	
}
