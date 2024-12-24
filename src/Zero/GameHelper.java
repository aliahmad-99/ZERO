package Zero;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameHelper {
	private static final Map<String, Item> items = new HashMap<>();
	private static final Map<String, Villians> villains = new HashMap<>();

	private static boolean levelone = true; // starting level one
	private static boolean leveltwo = false; // level two
	private static boolean levelthree = false; // level three
	private static boolean run = true; // running the game
	private static boolean warMedalReceived = false; // tracking if the war is received

	public static void setupGame(Player player, Location start) {
		// setting directions
		Direction north = Direction.NORTH;
		Direction south = Direction.SOUTH;
		Direction west = Direction.WEST;
		Direction east = Direction.EAST;

		// Locations
		Location mid = new Location("Studio 51", " a movie studio located in New Mecca.");
		Location end = new Location("Slaughterhouse", "  the famous Slaughterhouse in the Third District.");
		Location finish = new Location("Diamond District", " where all the final bosses are. ");

		// Set Exits
		start.setExit(north, mid);
		mid.setExit(south, start);
		mid.setExit(west, end);
		end.setExit(east, mid);
		end.setExit(south, finish);
		finish.setExit(north, mid);

		// create items
		createItems();

		// create villains
		createVillains();
		// welcome screen
		System.out.println("welcome to ZERO! a text-based adventure game.");
		System.out.println("you are " + player.getName());
	}

	// Method to create the items
	private static void createItems() {

		Item healthpotion = new Item("Health Potion", "Restores 30 HP", "Heals Player", "health");
		Item katana = new Item("KATANA", "A very, very sharp blade", "Increase attack power", "attack");
		Item warriorMedal = new Item("War Medal", "Only for the best of warriors", "Show off your glory", null);
		// adding items to hash map
		items.put("healthpotion", healthpotion);
		items.put("katana", katana);
		items.put("warriorMedal", warriorMedal);
	}

	// Method to create villains
	private static void createVillains() {
		Villians scientist = new Villians("Scientist", 2, 10, items.get("healthpotion"));
		Villians badguy = new Villians("Badguy", 40, 20, items.get("katana"));
		Villians finalboss = new Villians("V", 60, 15, null);
		// adding villains to hash map
		villains.put("scientist", scientist);
		villains.put("badguy", badguy);
		villains.put("finalboss", finalboss);
	}

	// get the player name
	public static String getPlayerName(Scanner scanner) {
		boolean nameCorrect = false;
		String name = "";
		// taking players name
		System.out.print("enter your name: ");
		while (!nameCorrect) {
			// the user should type agent zero for the game to go on
			name = scanner.nextLine().trim().toLowerCase();
			if (!name.equals("agent zero")) {
				System.out.print("that's not right. try again. enter your name: "); // if not agent zero then make the
																						// user type the name again
			} else {
				// if true then the game goes on
				nameCorrect = true;
				System.out.print("good. you know who you are. are you ready to play? (yes or no) : ");

				String ready = scanner.nextLine().trim().toLowerCase();
				// if user says no
				if (ready.equals("no")) {
					System.out.println("you chose to walk away. goodbye now. GAME OVER.");
					endGame();
					// if anything other than yes or no
				} else if (!ready.equals("yes")) {
					System.out.println("invalid choice. exiting game.");
					endGame();
					// if yes
				} else {
					System.out.println("The game will start now. Get ready for a journey through the "
							+ "new and refined world of a dystopian future.");
					System.out.println("type 'help' for help with commands or 'explore' to look around.");
				}
			}
		}
		return name;
	}

	/*
	 * processing all the commands from the game
	 * 
	 * @param input : the user input
	 * 
	 * @param player : the player
	 * 
	 * @param currentLocation : to track the players current location
	 * 
	 * @param levelOne, levelTwo , levelThree : track the current level
	 * 
	 * 
	 * 
	 */
	public static void processCommand(String input, Player player, Location currentLocation) {
		if (input.equals("help")) {
			System.out.println("commands : 'go [direction]' , 'explore', 'help all commands'");
			System.out.println("help all commands will show all the commands you can use in the game, but some are for later use");
			System.out.println("direction can be : 'east' , 'west' , 'north' , 'south' ");
		} else if (input.equals("explore")) {
			System.out.println("You are in " + currentLocation.getName() + ":" + currentLocation.getDescription());
		} else if (input.equals("help all commands")) {
			System.out.println("commands : 'go [direction]' , 'explore', 'attack', 'use [item]' , show health , show attack damage");
		} else if (input.startsWith("go ")) {
			// handle movement of the player
			handleMovement(input.substring(3), player, currentLocation); // take the input after 'go ' to trigger the location
		} else if (input.startsWith("use ")) {
			String itemName = input.substring(4).toLowerCase();// take the input after at 4th character and beyond
			if (items.containsKey(itemName)) {
				player.useItem(items.get(itemName));
				System.out.println("time to move on");
			} else {
				System.out.println("You don't have that item.");
			}
		} else if (input.equals("")) { // if enter is pressed
			System.out.println("try 'help'");
		} else if (input.equals("go")) {
			System.out.println("go where solider? specify a direction");
		} else if (input.equals("show health")) {
			System.out.println("current health = " + player.getHealth());
		} else if (input.equals("show attack damage")) {
			System.out.println("current attack damage = " + player.getDamage());
		}
		else if (input.equals("attack")) {
			handleBattle(player, currentLocation);
		} else if (input.equals("move on")) {
			System.out.println("go in a certain direction to move on!");
		} else if (input.equals("show off medal") && warMedalReceived) {
			showOffMedal();
			endGame(); // game ends after medal
		}

		else {
			System.out.println("Invalid input. Type 'help' for a list of commands.");
		}

	}

	/*
	 * handle the movements inside the game, go to a specific direction, trigger
	 * dialogue at certain locations and depending the level
	 * 
	 * @param input : input of player
	 * 
	 * @param Player : player
	 * 
	 * @param currentLocation : currentLocation
	 * 
	 * @param levelOne , levelTwo, levelThree : track the level of the of the player
	 */
	public static void handleMovement(String input, Player player, Location currentLocation) {
		try {
			Direction dir = Direction.valueOf(input.toUpperCase()); // converting string to UpperCase for enum use
			Location nextLocation = player.getLocation().getExit(dir); // nextLocation to go
			if (nextLocation != null) {

				player.setCurrentLocation(nextLocation); // change the location of the player
				System.out.println(
						"you are now in: " + player.getLocation().getName() + player.getLocation().getDescription());
				// the first location and level dialogue
				if (player.getLocation().getName().equals("Studio 51") && levelone) {
					System.out.println("scientist : you shouldn't have come here, zero");
					System.out.println(
							"zero : i came here to kill you. you have been injecting me with your compound V all my life");
					System.out.println("scientist : but... it wasn't m....");
					System.out.println("zero : shut up.you die now.");
					System.out.println("type 'attack' to fight");
					// the second location and level dialogue
				} else if (player.getLocation().getName().equals("Slaughterhouse") && leveltwo) {
					System.out.println("Badguy: you have it made far , zero. but you won't leave here alive.");
					System.out.println("zero: we'll see about that. BADGUY haha! who gave you that name? ");
					System.out.println("fight begins! type 'attack' to fight.");
					// the third location and level dialogue
				} else if (player.getLocation().getName().equals("Diamond District") && levelthree) {
					System.out.println(
							"V : you're a fighter zero. i'll give you that. but this is where it ends for you.");
					System.out.println("zero : i will finish you off, V");
					System.out.println("FINISH HIM. ATTACK");
				}

			} else {
				System.out.println("you can't go that way"); // if direction doesn't lead anywhere
			}
		} catch (IllegalArgumentException e) { // if invalid direction
			System.out.println("invalid direction");
		}
	}

	/*
	 * handling the battles between the player and the villains
	 *
	 * @param Player : player
	 * 
	 * @param currentLocation : Location
	 * 
	 */
	public static void handleBattle(Player player, Location currentLocation) {
		// for the first location and first villain
		if (player.getLocation().getName().equals("Studio 51") && levelone) {
			Villians scientist = villains.get("scientist");

			while (scientist.getHealth() > 0 && levelone) {
				player.attack(scientist);
				if (scientist.getHealth() > 0) {
					player.takeDamage(scientist.getDamage());
				}
			}
			if (player.getHealth() > 0) {
				System.out.println("you defeated the scientist, time to face your next challenge");
				System.out.println("you recieved a health potion. type'use healthpotion' to heal.");
				levelone = false;
				leveltwo = true;
			} else {
				System.out.println("you were defeated. game over.");
				return;
			}
			// for the second location and second villain

		} else if (player.getLocation().getName().equals("Slaughterhouse") && leveltwo) {
			Villians badguy = villains.get("badguy");

			while (badguy.getHealth() > 0 && player.getHealth() > 0) {
				player.attack(badguy);
				if (badguy.getHealth() > 0) {
					player.takeDamage(badguy.getDamage());
				}
			}
			if (player.getHealth() > 0) {
				System.out.println("badguy is down, move on to the final challenge");
				System.out.println("you received a katana. type 'use katana' to equip.");
				leveltwo = false;
				levelthree = true;

			}
			// for the third location and third villain

		} else if (player.getLocation().getName().equals("Diamond District") && levelthree) {
			Villians finalboss = villains.get("finalboss");

			while (finalboss.getHealth() > 0 && player.getHealth() > 0) {
				player.attack(finalboss);
				if (finalboss.getHealth() > 0) {
					player.takeDamage(finalboss.getDamage());
				}
			}

			if (player.getHealth() > 0) {
				System.out.println("congratulations! you defeated V and won the game!");
				player.addItem(items.get("warriorMedal"));
				warMedalReceived = true;
				System.out.println("you received the warrior medal! type 'show off medal' to show it off.");

			} else {
				System.out.println("you were defeated. GAME OVER!");
			}

		} else {
			System.out.println("there is no one to attack");
		}

	}

	// method to print the medal
	public static void showOffMedal() {
		System.out.println(" +++++++++++++");
		System.out.println("+             +");
		System.out.println("+    ZERO     +");
		System.out.println("+             +");
		System.out.println("+             +");
		System.out.println("+             +");
		System.out.println(" +++++++++++++");
		System.out.println("    ||||");
		System.out.println("    ||||");
		System.out.println(" +++++++++++++");
		System.out.println("+  WARRIOR  +");
		System.out.println("+   MEDAL   +");
		System.out.println(" +++++++++++++");
	}

	// end the game
	public static void endGame() {
		run = false;
	}

	// keep the game running
	public static boolean isRun() {
		return run;
	}

}