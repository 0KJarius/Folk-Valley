package entitypackage;

public enum EnemyCollection {
	PURSUER(new Enemy(200, "Pursuer", "persuer.png", "Your loving partner, but they won't go easy on you!")),
	PROTOSUER(new Enemy(300, "PROTOSUER", "protosuer.png", "Metal recreation of your lover. Do you have the heart to beat them?"));
	
	private Enemy enemy;
	private EnemyCollection(Enemy enemy) {this.enemy = enemy;}
	public Enemy getEnemy() {return enemy;}
}
