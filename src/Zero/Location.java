package Zero;
import java.util.*;

public class Location {
	private String name; // name of the room
	private String description; //description of the room
	private HashMap<Direction, Location> exits; // Map of the Locations with directions
	
	/*
	 * Constructor to create a new room
	 * 
	 * @param name : name of the room
	 * @param desciption : description of the room
	 */
	public Location(String name, String description) {
		this.name = name;
		this.description = description;
		this.exits = new HashMap<>();
		
	}
	/*
	 * Set an exit for a room in a specific direction 
	 * 
	 * @param dir : direction
	 * @param location : next location
	 */
	public void setExit(Direction dir, Location location) {
		exits.put(dir, location);
	}
	
	/*
	 * Connect the location to the specified direction
	 * @param dir :direction of the location#
	 * @return the connected location
	 */
	public Location getExit(Direction dir) {
		return exits.get(dir);
	}
	// getter for Location name
	public String getName() {
		return name;
	}
	// get description of the location
	public String getDescription() {
		return description;
	}
	/*
	 * Return all available location
	 * 
	 */
	public String getExits() {
		StringBuilder exitDescription = new StringBuilder("Exits: ");
		for (Direction dir : exits.keySet()) {
			exitDescription.append(dir.getName()).append("");		
		}
		return exitDescription.toString().trim();		
	}
	
	
}
