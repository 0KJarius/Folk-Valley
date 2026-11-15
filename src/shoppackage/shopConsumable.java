package shoppackage;
import itempackage.Item;
import utilities.EventBus;




public class shopConsumable  extends shopItem {

	private Item item;
	
    public shopConsumable(int price, String description, int rarity, Item item) {
        super(price, description, rarity);
        this.item = item;
    }
	
	@Override
	public void buy() {
		EventBus.player.inventory.add(item);
	}
	
	public Item getItem() {
		return item;
	}
	
}
