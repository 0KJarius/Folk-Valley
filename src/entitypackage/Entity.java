package entitypackage;

public class Entity {
	private int maxMoney;
	private int currentMoney;
	private String name;
	
	public Entity(int money, String name) 
	{
		this.currentMoney = money;
		this.maxMoney = money;
		this.name = name;
	}

	public int getCurrentMoney() {
		return currentMoney;
	}

	public void setMoney(int money) {
		this.currentMoney = money;
	}
	
	public void subMoney(int amount){
		currentMoney -= amount;
	}
	
	public void addMoney(int amount) {
		currentMoney += amount;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(int maxMoney) {
		this.maxMoney = maxMoney;
	}
	
	public void resetWallet() {
		currentMoney = maxMoney;
	}
	
}
