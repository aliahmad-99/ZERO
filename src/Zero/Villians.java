package Zero;

public class Villians {
	private String name; // name of monster
	private int health; // health of monster
	private int damage; // damage of monster
	private Item loot; // looted item after defeating monster
	// constructor for Villians
	public Villians(String name, int health, int damage, Item loot) {
		this.name = name;
		this.health = health;
		this.damage = damage;
		this.loot = loot;
	}
	
	public String getName() {
		return name;
	}
	public Item getLoot() {
		return loot;
	}
	public void attack(Player player) {
		System.out.println(name + " is attacking you!!!!");
		player.takeDamage(damage);
		System.out.println("your health has decrease by" + damage);
	}
	public void takeDamage(int amount) {
		health -= amount;
		System.out.println(name + "takes" + amount + "damage");
		if(health <= 0) {
			System.out.println("The " + name + "is defeated!");
		}
	}
	public int getHealth() {
		return health;
	}
	
}
