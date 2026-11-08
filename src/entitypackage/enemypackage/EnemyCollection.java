package entitypackage.enemypackage;

public enum EnemyCollection {
	PURSUER(new Pursuer()),
	PROTOSUER(new Protosuer());
	
	private Enemy enemy;
	private EnemyCollection(Enemy enemy) {this.enemy = enemy;}
	public Enemy getEnemy() {return enemy;}
}
