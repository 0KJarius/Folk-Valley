package shoppackage;

import utilities.EventBus;

public class ShopCardPack extends shopItem {
	private ShopPack pack;
	private ShopInventory inventory;
	
	
	public ShopCardPack(int price, String description, int rarity, ShopPack pack, ShopInventory inventory) {
		super(price, description, rarity);
		this.pack = pack;
		this.inventory = inventory;
	}


	@Override
	public void buy() {
        inventory.showSelect(pack, EventBus.player);
		
	}
	
	public ShopPack getPack() {
		return pack;
	}
	
	
	
}
