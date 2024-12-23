package Zero;

public class Player {
	
	private String name; // player name
	private int health; // player health
	private int attackdmg; //player dmg
	private Inventory inventory; // player inventory
	private Location currentLocation; // player currentLocation
	
	//Constructor to define a new player
	public Player(String name, int health,int attackdmg, Location startLocation) {
		this.name = name;
		this.health = health;
		this.attackdmg = attackdmg;
		this.currentLocation = startLocation;
		this.inventory = new Inventory();
	}
	/*
	 * move the player to another direction
	 * 
	 * @param dir : direction to move
	 */
	public void move(Direction dir) {
		Location nextLocation = currentLocation.getExit(dir);
		if (nextLocation != null) {
			currentLocation = nextLocation;
			System.out.println( "moving " + dir.getName() + " .");
		} else {
			System.out.println("can't go this way soldier!");
		}		
	}
	/*
	 * attack a villain
	 * 
	 * @param villian : villain
	 * 
	 */
	public void attack(Villians villians) {
		System.out.println("You are attacking " + villians.getName());
		villians.takeDamage(this.getDamage());
		if (villians.getHealth() <= 0) {
			inventory.addItem(villians.getLoot());
		}
	}
	/*
	 * take damage from a player
	 * 
	 * @param amount : amount of damage to be taken
	 */
	public void takeDamage(int amount) {
		health -= amount;
		System.out.println("You took " + amount + " of damage");
		
		if (health <= 0) {
			health  = 0; // make sure health doesn't drop below zero
		}
	}
	// adding an item to inventory
	public void addItem(Item item) {
		inventory.addItem(item);
		System.out.println(item.getName() + " is now yours");
	}
	//usage of item in an inventory
	public void useItem(Item item) {
		if(inventory.removeItem(item)) {
			item.use(this);
			
		} else {
			System.out.println("Item not found ");
		}
	}
	// increase player health after using an item
	public void increaseHealth(int amount) {
		this.health += amount;
		System.out.println("health increased by: " + amount);
	}
	// increase of player damage after gaining an item
	public void increaseDamage(int amount) {
		this.attackdmg += amount;
		System.out.println("Attack damage increased by: " + amount);
	}
	public void setCurrentLocation(Location location) {
		this.currentLocation = location;
	}
	// getter for inventory
	public Inventory getInventory() {
		return inventory;
	}
	// getter for player current location
	public Location getLocation() {
		return currentLocation;
	}
	//getter for player name
	public String getName() {
		return name;
	}
	// getter for player damage
	public int getDamage() {
		return attackdmg;
	}
	// getter for player health
	public int getHealth() {
		return health;
	}
	
}
