package Zero;

import java.util.Scanner;

public class ZeroEngine {
    public static void main(String[] args) {
    	// start Location
        Location start = new Location("Club Neon", " a popular dance club in the Second District");
        
        // Create Player
        Player player = new Player("Agent Zero", 50, 20, start);
        //set up game
        GameHelper.setupGame(player, start);
        // Scanner
        Scanner scanner = new Scanner(System.in);
        // checking name
       GameHelper.getPlayerName(scanner);
        // game running
        while (GameHelper.isRun()) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            GameHelper.processCommand(input, player, player.getLocation());
        }
       scanner.close();
    }
   
}
