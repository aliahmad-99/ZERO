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
	
	public void attack(Villians villians) {
		System.out.println(" You are attacking " + villians.getName());
		villians.takeDamage(this.getDamage());
		if (villians.getHealth() <= 0) {
			System.out.println("You defeated " + villians.getName() );
			inventory.addItem(villians.getLoot());
		}
	}
	public void takeDamage(int amount) {
		health -= amount;
		System.out.println("You took " + amount + " of damage");
		
		if (health <= 0) {
			health  = 0; // make sure health doesnt drop below zero
			System.out.println("you have been defeated! GAME OVER!!");
			// add what happens after defeat
		}
	}
	
	public void addItem(Item item) {
		inventory.addItem(item);
		System.out.println(item.getName() + " is now yours");
	}
	public void useItem(Item item) {
		if(inventory.removeItem(item)) {
			System.out.println("Using " + item.getName());
		} else {
			System.out.println(" Item not found ");
		}
	}
	public Inventory getInventory() {
		return inventory;
	}
	public Location getLocation() {
		return currentLocation;
	}
	public String getName() {
		return name;
	}
	public int getDamage() {
		return attackdmg;
	}
}
