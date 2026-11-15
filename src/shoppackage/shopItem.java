package shoppackage;

public abstract class shopItem {

	protected int price;
	protected String description;
	protected int rarity;
	public shopItem(int price, String description, int rarity) {
		super();
		this.price = price;
		this.description = description;
		this.rarity = rarity;
	}
	public int getPrice() {
		return price;
	}
	public String getDescription() {
		return description;
	}
	public int getRarity() {
		return rarity;
	}
	
	
	public abstract void buy();
	
	
}
