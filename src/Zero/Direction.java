package Zero;

public enum Direction {
	NORTH("north"),
	SOUTH("south"),
	EAST("east"),
	WEST("west");

	// setting user friendly name for direction(north,south..)
	private final String name;
	
	Direction(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	/*
	 * Matching user input string to enum
	 *@param dir the input string
	 *@return the direction, null if not found
	 */
	public static Direction fromString(String direction) {
		for(Direction dir : Direction.values()) {
			if(dir.getName().equalsIgnoreCase(direction)) {
				return dir;
			}
		}
		return null;
	}

	
}
