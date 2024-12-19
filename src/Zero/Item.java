package Zero;

public class Item {

	private String name; // name of item
	private String description; // Description of the item
	private String effect; 	// effect of using the item
	private String itemType; // type of the item
	
	// constructor for item
	
	public Item(String name, String description, String effect, String itemType) {
		this.name = name;
		this.description = description;
		this.effect = effect;
		this.itemType = itemType;
	}
	/*
	 * Usage of items 
	 * 
	 * @param Player : Player
	 */
	public void use(Player player) {
		System.out.println("You will use the " + name + ": " + effect + ".");
		if(this.itemType == "Health") {
			player.increaseHealth(30);
			} else if (this.itemType == "Attack") {
				player.increaseDamage(30);
			} else {
				return;
			}
			
	}
	// getter for item name
	public String getName() {
		return name;
	}
	//getter for item Description
	public String getDescription() {
		return description;
	}
	//getter for effect
	public String getEffect() {
		return effect;
	}
	//getter for itemType
	public String getItemType() {
		return itemType;
	}
	
	
}
