package Zero;
import java.util.Scanner;
public class ZeroEngine {
	public static void main(String[] args) {
		// create directions
		Direction north = Direction.NORTH;
		Direction south = Direction.SOUTH;
		Direction west = Direction.WEST;
		Direction east = Direction.EAST;
		
		// craete Locations
		
		Location start = new Location("Club Neon", " is a popular dance club in the Second District");
		Location mid = new Location("Studio 51", " is a movie studio located in the New Mecca");
		Location end = new Location("Slaughterhouse", " is the famous Slaugherhouse in the Third District");
		
		// set exits for the locations
		start.setExit(north, mid);
		mid.setExit(south, start);
		mid.setExit(west, end);
		end.setExit(east,mid);
		
		
		//Create items
		Item healthPotion = new Item("Health Potion", "Restores 20 HP", "Heals Player", true);
		Item katana = new Item("KATANA", "a very very sharp blade", "Increase attack power", true);
		
		
		// Create Monsters
		Villians scientist = new Villians ("Scientist" , 2 , 5, healthPotion);
		Villians badguy = new Villians("badguy", 30, 2, katana);
		Villians finalboss = new Villians("V",50,2,null);
		// Create Player 
		Player player = new Player("agent zero", 100, 20, start);
		
		//scanner
		Scanner scanner = new Scanner (System.in);
		
		System.out.println("Welcome to ZERO! A text-baed adventure game");
		System.out.println("You are " + player.getName());
		System.out.println("enter your name :");
		String name = scanner.nextLine().trim().toLowerCase();
		boolean nmw = true;
		while(nmw) {
			if(!name.equals("agent zero")) {
			System.out.print("enter your name: ");
			name = scanner.nextLine().trim().toLowerCase();
			
			} else  {
				System.out.println("thats true, you are not who you say you are. you are agent zero. now you can start playing. are you ready?");
				nmw = false;
				
			}
		
		}
		
		boolean run = true;
		boolean levelOne = false;
		boolean levelTwo = false;
		
		
	
		
	scanner.close();
	
	
	
	
	
	}
}
