package Zero;

public class Item {

	private String name; // name of item
	private String description; // Decsription of the item
	private String effect; 	// effect of using the item
	private boolean usable; // usable or not
	
	// constructor for item
	
	public Item(String name, String description, String effect, boolean usable) {
		this.name = name;
		this.description = description;
		this.effect = effect;
		this.usable = usable;	
	}
	
	public void use(Player player) {
		if(usable) {
			System.out.println("You feel the effect of " + name + ": " + effect + ".");
			// specific effects here
		} else {
			System.out.println(name + "can't be used");
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	
	
	
}
