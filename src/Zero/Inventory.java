package Zero;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private List<Item> items;  // List of items in inventory
	private int capacity; // capacity of inventory
	// contructor for new inventory
	public Inventory() {
		this.items = new ArrayList<>();
		this.capacity = 3; // default maximum capacity a player can hold
	}
	
	public boolean addItem(Item item) {
		if (items.size() < capacity ) {
			items.add(item);
			return true;
		} else {
			System.out.println("Inventory if full soldier!");
			return false;
		}
	}
	
	public boolean removeItem(Item item) {
		return items.remove(item);
	}
	public Item getItem() {
		return items.get(0);
	}
	public void listItems() {
		System.out.println("The items in your possesion: ");
		for(Item item : items) {
			System.out.println(item.getName() + item.getDescription());
		}
	}
	
}
