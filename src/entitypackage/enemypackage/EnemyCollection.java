package entitypackage.enemypackage;

public enum EnemyCollection {
	PURSUER(new Enemy(200, "Pursuer", "persuer.png", "Your loving partner, but they won't go easy on you!")),
	PROTOSUER(new Protosuer());
	
	private Enemy enemy;
	private EnemyCollection(Enemy enemy) {this.enemy = enemy;}
	public Enemy getEnemy() {return enemy;}
}
