package entitypackage.enemypackage;

public enum EnemyCollection {
	PURSUER(new Pursuer()),
	PROTOSUER(new Protosuer()),
	NUMEROUNO(new NumeroUno()),
	MONOPOLEE(new MonopolyMan()),
	REFEREE(new Referee()),
	BUSINESS(new Business()),
	GOODU(new Goodu()),
	IRS(new IRS());
	
	private Enemy enemy;
	private EnemyCollection(Enemy enemy) {this.enemy = enemy;}
	public Enemy getEnemy() {return enemy;}
}
