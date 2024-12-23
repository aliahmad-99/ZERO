
package Zero;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private List<Item> items;  // List of items in inventory
	private int capacity; // capacity of inventory
	// constructor for new inventory
	public Inventory() {
		this.items = new ArrayList<>();
		this.capacity = 3; // default maximum capacity a player can hold
	}
	// addition of item
	public boolean addItem(Item item) {
		if (items.size() < capacity ) {
			items.add(item);
			return true;
		} else {
			
			return false;
		}
	}
	// remove item from inventory
	public boolean removeItem(Item item) {
			return items.remove(item);
		}
	
	//getter for items
	public List<Item> getItems(){
		return items;
	}
	//getter for capacity
	public int getCapacity() {
		return capacity;
	}
	// return all available items
	public void listItems() {
		System.out.println("The items in your possesion: ");
		for(Item item : items) {
			System.out.println(item.getName() + " " + item.getDescription());
		}
	}
	
}
