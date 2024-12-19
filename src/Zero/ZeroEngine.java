package Zero;

import java.util.Scanner;

public class ZeroEngine {
	public static void main(String[] args) {
		// create directions
		Direction north = Direction.NORTH;
		Direction south = Direction.SOUTH;
		Direction west = Direction.WEST;
		Direction east = Direction.EAST;

		// create Locations
		Location start = new Location("Club Neon", " is a popular dance club in the Second District");
		Location mid = new Location("Studio 51", " is a movie studio located in the New Mecca");
		Location end = new Location("Slaughterhouse", " is the famous Slaughterhouse in the Third District");
		Location currentLocation = start;

		// set exits for the locations
		start.setExit(north, mid);
		mid.setExit(south, start);
		mid.setExit(west, end);
		end.setExit(east, mid);

		// Create items
		Item healthPotion = new Item("Health Potion", "Restores 30 HP", "Heals Player", "health");
		Item katana = new Item("KATANA", "A very, very sharp blade", "Increase attack power", "attack");
		Item warriorMedal = new Item("War Medal", "Only for the best of warriors", "Show off your glory", null);

		// Create Monsters
		Villians scientist = new Villians("Scientist", 2, 10, healthPotion);
		Villians badguy = new Villians("Badguy", 40, 20, katana);
		Villians finalboss = new Villians("V", 60, 15, null);

		// Create Player
		Player player = new Player("Agent Zero", 100, 20, start);

		// Scanner
		Scanner scanner = new Scanner(System.in);

		System.out.println("welcome to ZERO! a text-based adventure game.");
		System.out.println("you are " + player.getName());
		System.out.println("enter your name: ");
		String name = scanner.nextLine().trim().toLowerCase();
		boolean namecorrect = false;

		while (!namecorrect) {
			if (!name.equals("agent zero")) {
				System.out.print("that's not right. try again. enter your name: ");
				name = scanner.nextLine().trim().toLowerCase();
			} else {
				namecorrect = true;
				System.out.println("good. we know who you are. are you ready to play? (yes or no)");
				String ready = scanner.nextLine().trim().toLowerCase();
				if (ready.equals("no")) {
					System.out.println("you chose to walk away. goodbye now. GAME OVER.");
					return;
				} else if (!ready.equals("yes")) {
					System.out.println("invalid choice. exiting game.");
					return;
				}
			}
		}

		System.out.println("The game will start now. Get ready for a journey through the "
				+ "new and refined world of a dystopian future.");
		System.out.println("type 'help' for help with commands or 'explore' to look around.");

		boolean run = true;
		boolean levelone = true;
		boolean leveltwo = false;
		boolean levelthree = false;
		boolean warriorMedalReceived = false; // Track if the Warrior Medal is received

		while (run) {
			System.out.println("> ");
			String input = scanner.nextLine().trim().toLowerCase();

			if (input.equals("help")) {
				System.out.println("commands : 'go [direction]' , 'explore', 'help all commands '");
			} else if (input.equals("explore")) {
				System.out.println("you are in " + currentLocation.getName() + currentLocation.getDescription());

			} else if (input.equals("help all commands")) {
				System.out.println("commands : 'go [direction]' , 'explore', 'attack', 'use [item]'");
			} else if (input.startsWith("go ")) {
				String direction = input.substring(3).toUpperCase();
				try {
					Direction dir = Direction.valueOf(direction);
					Location nextLocation = currentLocation.getExit(dir);
					if (nextLocation != null) {
						currentLocation = nextLocation;
						System.out.println(
								"you are now in: " + currentLocation.getName() + currentLocation.getDescription());

						if (currentLocation.getName().equals("Studio 51") && levelone) {
							System.out.println("scientist : you shouldn't have come here, zero");
							System.out.println(
									"zero : i came here to kill you. you have been injecting me with your compound V all my life");
							System.out.println("scientist : but... it wasn't m....");
							System.out.println("zero : shut up.you die now.");
							System.out.println("type 'attack' to fight");
						} else if (currentLocation.getName().equals("Slaughterhouse") && leveltwo) {
							System.out.println("Badguy: you have it made far , zero. but you won't leave here alive.");
							System.out.println("zero: we'll see about that. BADGUY haha! who gave you that name? ");
							System.out.println("fight begins! type 'attack' to fight.");
						} 

					} else {
						System.out.println(" you can't go that way");
					}
				} catch (IllegalArgumentException e) {
					System.out.println("invalid direction");
				}

			} else if (input.equals("attack")) {
				if (currentLocation.getName().equals("Studio 51") && levelone) {
					while (scientist.getHealth() > 0 && levelone) {
						player.attack(scientist);
						if (scientist.getHealth() > 0) {
							player.takeDamage(scientist.getDamage());
						}
					}
					if (player.getHealth() > 0) {
						System.out.println("you recieved a health potion. type'use healthpotion' to heal.");
						levelone = false;
						leveltwo = true;
					} else {
						System.out.println("you were defeated. game over.");
						break;
					}

				} else if (currentLocation.getName().equals("Slaughterhouse") && leveltwo) {
					while (badguy.getHealth() > 0 && player.getHealth() > 0) {
						player.attack(badguy);
						if (badguy.getHealth() > 0) {
							player.takeDamage(badguy.getDamage());
						}
					}
					if (player.getHealth() > 0) {
						System.out.println("you defeated badguy");
						System.out.println("you received a katana. type 'use katana' to equip.");
						leveltwo = false;
						levelthree = true;
					} else {
						System.out.println("you were defeated. game over");
						break;
					}

				} else if (currentLocation.getName().equals("Slaughterhouse") && levelthree) {
					while (finalboss.getHealth() > 0 && player.getHealth() > 0) {
						player.attack(finalboss);
						if (finalboss.getHealth() > 0) {
							player.takeDamage(finalboss.getDamage());
						}
					}

					if (player.getHealth() > 0) {
						System.out.println("congratulations! you defeated V and won the game!");
						player.addItem(warriorMedal);
						warriorMedalReceived = true;
						System.out.println("you received the warrior medal! type 'show off medal' to show it off.");
					} else {
						System.out.println("you were defeated. game over.");
						break;
					}

				} else {
					System.out.println("thre is no one to attack");
				}
				

			} else if(input.equals("use healthpotion") && leveltwo) {
				healthPotion.use(player);
				System.out.println("you used the health potion. HP restored by 30");
				System.out.println("you are stronger agent zero. time to move on");
			} else if(input.equals("use katana") && levelthree) {
				System.out.println("you equipped the katana. right now you are merciless. attack power increased by 20.");
				player.increaseDamage(20);
				System.out.println("time to face the last challenge.");
				if (currentLocation.getName().equals("Slaughterhouse") && levelthree) {
					System.out.println(
							"V : you're a fighter zero. i'll give you that. but this is where it ends for you.");
					System.out.println("zero : i will finish you off, V");
					System.out.println("FINISH HIM. ATTACK");
				}
			} else if(input.equals("show off medal") && warriorMedalReceived ){
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
                run =  false;
			} else {
                System.out.println("invalid input. type 'help' for a list of commands.");
            }
        }

        scanner.close();
    }
}
