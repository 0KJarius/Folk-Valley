package entitypackage;

public class Entity {
	private int money;
	private String name;
	
	public Entity(int money, String name) 
	{
		this.money = money;
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public void subMoney(int amount){
		money -= amount;
	}
	
	public void addMoney(int amount) {
		money += amount;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
