package Zero;

public class Villians {
	private String name; // name of monster
	private int health; // health of monster
	private int damage; // damage of monster
	private Item loot; // looted item after defeating monster
	// constructor for Villains
	public Villians(String name, int health, int damage, Item loot) {
		this.name = name;
		this.health = health;
		this.damage = damage;
		this.loot = loot;
	}
	// getter for villain name
	public String getName() {
		return name;
	}
	// getter for villain loot
	public Item getLoot() {
		return loot;
	}
	/*
	 * attack the player
	 * 
	 * @param player : Player
	 */
	public void attack(Player player) {
		System.out.println(name + " is attacking you!!!!");
		player.takeDamage(damage);
		System.out.println("your health has decrease by" + damage);
	}
	/*
	 * take damage from the player
	 * 
	 * @param amount : amount of damage
	 */
	public void takeDamage(int amount) {
		health -= amount;
		System.out.println(name + " takes " + amount + " damage ");
		if(health <= 0) {
			System.out.println(name + " is defeated!");
		}
	}
	// getter for villain health
	public int getHealth() {
		return health;
	}
	public int getDamage() {
		return damage;
	}
	
	
}
