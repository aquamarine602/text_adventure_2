package cspik3_text;

/**
 * CURRENT PROBLEMS:
 * box and lamp and bulb show in room despite being in inventory
 * open method descriptions
 * attack method allows all objects, including objects not in inventory, to be used
 */

import java.util.Scanner;
import static java.lang.System.out;

public class Game {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String user = "";
		
		// title screen
		while (true) {		// while system hasn't exited
			// title screen options
			out.println("Escape by Chloe Spilker\n"
					+ "-----------------------\n"
					+ "Enter 'P' to play game.\n"
					+ "Enter 'N' to see noun list.\n"
					+ "Enter 'I' to see instructions.\n"
					+ "Enter 'C' to see current issues.\n"
					+ "Enter 'X' to quit game.\n\n");
			
			out.print(">");
			user = input.nextLine();
			out.println("\n\n");
			user = user.toLowerCase();
			
			// options
			if (user.equals("p")) {
				game(input);	// plays method 'game'
			} else if (user.equals("n")) {
				out.println("The nouns in this game are:\n"
						+ "box\n"
						+ "lamp\n"
						+ "bulb\n"
						+ "hammer\n"
						+ "stool\n"
						+ "paper\n\n"
						+ "The verb specific nouns are:\n"
						+ "self\n"
						+ "safe\n\nPress enter to continue.");
				input.nextLine();
				out.println("\n\n");
			} else if (user.equals("i")) {		// gives user verb instruction
				out.println("Use 'look' to study your surroundings.\n"
						+ "Use 'take (object)' to add an object to your inventory.\n"
						+ "Use 'open (object)' to open an object.\n"
						+ "Use 'go (direction)' to move.\n"
						+ "Use 'attack (object)' to attack or destroy an object.\n"
						+ "Use 'read (object)' to read an object.\n\nPress enter to continue.");
				input.nextLine();
				out.println("\n\n");
			} else if (user.equals("x")) {
				System.exit(0);		// exits program
			} else if (user.equals("c")) {	// gives user list of known issues in game
				out.println("Current known issues include:\n"
						+ "----------------------------\n"
						+ "The ascii items and descriptions in 'room_center' will always show up,\n"
						+ "even if they are in the player's inventory.\n\n"
						+ "The 'open' method descriptions need to be narrowed down. The hammer and\n"
						+ "stool will always show up in the box and safe even if taken.\n\n"
						+ "The 'attack' method will allow the user to attack with any noun in list\n"
						+ "of nouns, even if that noun isn't present or in user's inventory.\n\n"
						+ "Press enter to continue.");
				input.nextLine();
				out.println("\n\n");
			}
		}
		
		
	}
	
	public static void game(Scanner input) {
		
		// checks if items are in inventory
		boolean box = false;
		boolean lamp = false;
		boolean bulb = false;
		boolean hammer = false;
		boolean stool = false;
		boolean paper = false;
		
		// checks if items are present
		boolean bulb_present = true;
		boolean hammer_present = false;
		boolean stool_present = false;
		boolean paper_present = false;
		boolean paper_read = false;
		boolean up = false;
		
		// string for users location -- starting location
		String location = room_center(lamp, box, bulb);
		
		// introduces character to game
		out.println(" .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \r\n" + 
				"| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\r\n" + 
				"| |  _________   | || |    _______   | || |     ______   | || |      __      | || |   ______     | || |  _________   | || |              | |\r\n" + 
				"| | |_   ___  |  | || |   /  ___  |  | || |   .' ___  |  | || |     /  \\     | || |  |_   __ \\   | || | |_   ___  |  | || |      _       | |\r\n" + 
				"| |   | |_  \\_|  | || |  |  (__ \\_|  | || |  / .'   \\_|  | || |    / /\\ \\    | || |    | |__) |  | || |   | |_  \\_|  | || |     | |      | |\r\n" + 
				"| |   |  _|  _   | || |   '.___`-.   | || |  | |         | || |   / ____ \\   | || |    |  ___/   | || |   |  _|  _   | || |     | |      | |\r\n" + 
				"| |  _| |___/ |  | || |  |`\\____) |  | || |  \\ `.___.'\\  | || | _/ /    \\ \\_ | || |   _| |_      | || |  _| |___/ |  | || |     | |      | |\r\n" + 
				"| | |_________|  | || |  |_______.'  | || |   `._____.'  | || ||____|  |____|| || |  |_____|     | || | |_________|  | || |     |_|      | |\r\n" + 
				"| |              | || |              | || |              | || |              | || |              | || |              | || |     (_)      | |\r\n" + 
				"| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\r\n" + 
				" '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ");
		out.println("\n\nYou wake up in a dark room.");
		out.println("The last memory you have is of\nyour head hitting your pillow.");
		
		// player input continues until a system exit occurs
		while (true) {
			// user types in verb and possible noun
			out.print("\n\n>");
			String words = input.nextLine();
			words = words + " " + " ";		// makes sure noun can be empty -- causes weird spacing issues
				
			// gets index of spaces
			int space = words.indexOf(" ");
			int last_space = words.lastIndexOf(" ");
				
			// declares user's verb and noun
			String verb = words.substring(0, space);
			String noun = words.substring(space + 1, last_space);
			verb.toLowerCase();		// sends verb and noun to lowercase
			noun.toLowerCase();
			
			// determines action based on user's verb
			if (verb.equals("look")) {		// describes player's surroundings
				out.println(location);
				
			} else if (verb.equals("take")) {		// takes an object
				boolean boo1 = noun_check(noun);	// checks for usable noun
				if (boo1 == true) {
					if (noun.equals("box ")) {		// if statement to use correct item as boolean
						box = take2(box, noun, true);
					} else if (noun.equals("lamp ")) {
						lamp = take2(lamp, noun, true);
					} else if (noun.equals("bulb ")) {
						bulb = take2(bulb, noun, bulb_present);
					} else if (noun.equals("hammer ")) {
						hammer = take2(hammer, noun, hammer_present);
					} else if (noun.equals("stool ")) {
						stool = take2(stool, noun, stool_present);
					} else if (noun.equals("paper ")) {
						paper = take2(paper, noun, paper_present);
					}
				}
				
			} else if (verb.equals("open")) {	// opens object (box and safe)
				boolean boo1 = false;			// checks for usable noun
				if (noun.equals("safe ")) {
					if (paper_read == true) {
						boo1 = true;
					} else {		// if the paper hasn't been read
						out.println("\n\nYou stare at the closed safe.\n"
								+ "Don't safes need passwords?");
					}
				} else {
					boo1 = noun_check(noun);
				}
				
				if (boo1 == true) {		// determines proper boolean for method
					if (noun.equals("safe ")) {
						stool_present = open(noun, box, bulb, paper_read, stool_present, hammer_present);
						
					} else if (noun.equals("box ")) {
						hammer_present = open(noun, box, bulb, paper_read, stool_present, hammer_present);
						
					} else if (noun.equals("bulb ")) {
						out.println("\n\nTry breaking the bulb.");
						
					} else {
						out.println("\n\nYou can't open " + noun + ".");
					}
				}
				
			} else if (verb.equals("go")) {		// takes the player to different locations of room
												// does not affect what player can and cannot pick up
				location = go(location, noun, box, lamp, bulb_present, stool_present, up);
				
			} else if (verb.equals("attack")) {		// attacks or breaks object -- bulb or self
				bulb_present = attack(noun, bulb, bulb_present);
				if (bulb_present == false) {		// if bulb is destroyed, paper is present
					paper_present = true;
				}
				
			} else if (verb.equals("read")) {		// reads object -- paper
				paper_read = read(noun, paper_read, paper, paper_present);
				
			} else {		// if the verb wasn't listed
				out.println("I don't know '" + verb + "'.");
			}
			
			// if the player has the stool and removed the lamp
			if (up == false) {
				if (stool == true && lamp == true) {
					out.println("\n\n\nPress enter to place the stool\n"
							+ "underneath the hole in the ceiling.");
					input.nextLine();
					up = true;
					out.println("\n\nPlaced.");
				}
			}
		}
		
	}
	
	public static boolean noun_check(String one) {		// checks to see if noun is applicable
		boolean boo1 = true;
		
		if (one.equals("box ") || one.equals("lamp ") || one.equals("bulb ") || one.equals("stool ") || one.equals("paper ") || one.equals("hammer ")) {
		} else {
			out.println("\nI don't know the noun '" + one + "'.");	// if noun is unknown
			boo1 = false;
		}
		
		return boo1;
		
	}
	
	public static String room_center(boolean lamp, boolean box, boolean bulb) {		// the center of the room desc
		String words = "  _   _   _   _     _   _   _   _   _   _  \r\n" + 
				" / \\ / \\ / \\ / \\   / \\ / \\ / \\ / \\ / \\ / \\ \r\n" + 
				"( R | o | o | m ) ( C | e | n | t | e | r )\r\n" + 
				" \\_/ \\_/ \\_/ \\_/   \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ ";
		
		// room desc
		words = words + "\n\nYou are in the center of a cold\n"
				+ "and damp room. The shape of the room\n"
				+ "seems to be a perfect square,\n"
				+ "though the room itself isn't very\n"
				+ "large. There are no doors, there are\n"
				+ "no windows.\n\n";
		
		// if lamp isn't taken -- CURRENTLY NOT WORKING
		if (lamp == false) {
			words = words + 
				"There is a lamp dangling from the ceiling.\n"
				+ "The ceiling is crumbling around the hinges\n"
				+ "of the lamp.\n\n";
			
			if (bulb == false) {		// if bulb isn't taken -- NOT WORKING
				words = words +
						"Inside the lamp, there is a bulb\n"
						+ "that is screwed in loosely.\n\n";
				
				words = words +
						" .=====.\r\n"
						+ "   |_|\r\n"
						+ "   | |\r\n"
						+ "(-------)\r\n"
						+ "(  (o)  )\n\n";
				
			} else {		// if bulb is taken -- NOT WORKING
				words = words +
						" .=====.\r\n"
						+ "   |_|\r\n"
						+ "   | |\r\n"
						+ "(-------)\r\n"
						+ "(       )\n\n";
			}
			
		}
		
		if (box == false) {		// if box isn't taken -- CURRENTLY NOT WORKING
			words = words +
				"There is a small box sitting in the\n"
				+ "center of the room.\n\n"
				+ "\r\n" + 
				"                      _.-+.\r\n" + 
				"                 _.-\"\"     '.\r\n" + 
				"             +:\"\"            '.\r\n" + 
				"             J \\               '.\r\n" + 
				"              L \\             _.-+\r\n" + 
				"              |  '.       _.-\"   |\r\n" + 
				"              J    \\  _.-\"       L\r\n" + 
				"               L    +\"          J\r\n" + 
				"               +    |           |\r\n" + 
				"                \\   |          .+\r\n" + 
				"                 \\  |       .-'\r\n" + 
				"                  \\ |    .-'\r\n" + 
				"                   \\| .-'\r\n" + 
				"                    +'   \n\n";
		}
		
		// tells player of only direction worth value
		words = words +
				"To the south is one wall of the room.\n"
				+ "There is a safe connected to that wall.";
		
		return words;
		
	}
	
	public static String south_wall(boolean a) {		// describes south wall -- no stool described
		String words = ".-.-. .-.-. .-.-. .-.-. .-.-.      .-.-. .-.-. .-.-. .-.-. \r\n" + 
				"( S .'( o .'( u .'( t .'( h .'.-.-.( W .'( a .'( l .'( l .' \r\n" + 
				" `.(   `.(   `.(   `.(   `.(  '._.' `.(   `.(   `.(   `.(   ";
		words = words + "\n\nBesides a colorless tiled wall,\n" +
				"there is a safe a part of the wall.\n\n"
				+ "         _.---.._ .   \r\n" + 
				"     .-\"'        `;\"--,\r\n" + 
				"     L\"\"--..__    | .'J\r\n" + 
				"     |:`' - ..`\"\"-.'  |\r\n" + 
				"     |.        `\":J   F\r\n" + 
				"     J:   ,-.    .|  J\r\n" + 
				"     J    FG)|   ;F  |\r\n" + 
				"      L.  `-'   .J   F '\r\n" + 
				"      L:        :|  J/, ,   .my\r\n" + 
				"      |'._      :| /^_,- '  \\-)\r\n" + 
				"      `c-.__'- .;F/ =_,;_\"  F\"J\r\n" + 
				"    - -=_,) `\"--3'";
		
		words = words + "\nTo the north is the center\n"
				+ "of the room.";
		
		return words;
	}
	
	public static String up(String a) {		// takes player up and out of the roof -- ends game
		out.println("\n\nYou jump on to the roof,\n"
				+ "you can hear crickets chirping.\n\n"
				+ "You have escaped.\n\n"
				+ "\nFIN.");
		System.exit(0);
		return null;
	}
	
	public static boolean take2(boolean boo1, String noun, boolean present) {		// takes object
																				// take1 was v1 and sucked
		if (present == false) {		// if the object isn't present in the room
			out.println("There is no " + noun + "present.");
		} else {
			if (boo1 == true) {		// if user already has object
				out.println("You already have " + noun + ".");
			} else {
				
				// special descriptions for items when taken
				if (noun.equals("lamp ")) {
					out.println("\n\nA hole in the ceiling of the roof\n"
							+ "appears as the lamp is torn away.\n"
							+ "Moonlight shines through the hole.\n\n");
					
				} else if (noun.equals("bulb ")) {
					out.println("\n\nThe room noticibly dims, but you can\n"
							+ "still see.\n");
				}
				
				out.println("Taken.");
				boo1 = true;		// object is taken
			}
		}
		
		return boo1;

		
	}
	
	// opens object -- i need to add whether or not the item has already been taken**
	public static boolean open(String noun, boolean box, boolean bulb, boolean paper_read, boolean stool_present, boolean hammer_present) {
		boolean returned = false;
		
		// opens safe
		if (noun.equals("safe ")) {
			out.println("\n\nYou pull open the safe. It opens\n"
					+ "almost effortlessly.\n\n"
					+ "Inside the safe you see an old wooden\n"
					+ "stool.\n\n" + 
					" _____\r\n" + 
					"|_/_\\_|\r\n" + 
					" || ||\r\n" + 
					" || ||\r\n" + 
					" ||=||\r\n" + 
					" || || \r\n\n");
			returned = true;	// stool is present
		
		// opens box
		} else if (noun.equals("box ")) {
			out.println("\n\nYou open the box. Inside the box\n"
					+ "is a hammer.\n\n"
					+ "       ,\r\n" + 
					"      /(  ___________\r\n" + 
					"     |  >:===========`\r\n" + 
					"      )(\r\n" + 
					"      \"\"\r\n");
			returned = true;	// hammer is present
				
		}
		
		return returned;

	}
	
	// sends player to desired location w/ description
	public static String go(String location, String noun, boolean box, boolean lamp, boolean bulb_present, boolean stool_present, boolean up) {
		
		if (noun.equals("north ")) {		// player goes north -- room center
			out.println("\n\nYou go north to the room center.\n"
					+ "Farther north is an empty wall.");
			location = room_center(lamp, box, bulb_present);
			
		} else if (noun.equals("east ")){		// player goes east -- no where
			out.println("\n\nYou go to the east wall."
					+ "There is an empty wall here. It\n"
					+ "is covered in white tiles.");
			location = "\n\nThere is an empty wall here. It\n"
					+ "is covered in white tiles.";
			
		} else if (noun.equals("south ")) {		// player goes south -- south wall
			out.println("\n\nYou go to the south wall.");
			location = south_wall(stool_present);
			
		} else if (noun.equals("west ")) {		// player goes west -- no where
			out.println("\n\nYou go to the west wall."
					+ "There is an empty wall here. It\n"
					+ "is covered in white tiles.");
			location = "\n\nThere is an empty wall here. It\n"
					+ "is covered in white tiles.";
			
		} else if (noun.equals("up ")) {		// player goes up -- escapes
			if (up == true) {		// can only go up if up is true
				out.println("\n\nYou climb up.");
				location = up("");
			} else {
				out.println("\n\nYou cannot go up.");
				
				if (lamp == true) {// if there is a hole in the ceiling
					out.println("The hole in the ceiling is"
							+ "just beyond reach.\n");
				}
			}
		}
		
		return location;
	}
	
	// attacks and destroys objects -- ending two
	public static boolean attack(String noun, boolean bulb_present, boolean bulb) {
		Scanner input = new Scanner(System.in);
		
		// noun check
		boolean boo1 = false;
		if (noun.equals("self ")) {
			boo1 = true;
		} else {
			boo1 = noun_check(noun);
		}
		
		// boo2 and noun2 initialize
		boolean boo2 = false;
		String noun2 = "";
		
		if (boo1 == true) {
			
			// asks player what they are attacking with, has to be part of nouns -- BUG: you don't need
			out.println("\nWhat are you attacking " + noun + " with?");				// the object to use it
			out.print(">");
			noun2 = input.nextLine();
			noun2 = noun2 + " ";
			boo2 = noun_check(noun2);
			
			if (boo2 == true) {		// if noun2 is accepted noun
			
			if (noun.equals("bulb ")) {		// if noun is bulb
				if (bulb == true) {		// if user has bulb in inventory
					if (bulb_present == true) {		// if bulb has broken yet
						if (noun2.equals("hammer ")) {		// bulb has to be broken by hammer
							out.println("\n\nThe bulb bursts as the hammer\n"
									+ "comes in contact with it.\n"
									+ "A small leaf of paper flies out from\n"
									+ "where the bulb once was.");
							bulb_present = false;		// breaks bulb
						} else {// tells user to try attacking with hammer
							out.println("\n\nTry attacking the bulb with the hammer.");
						}
					} else {
						out.println("The bulb is already broken.");
					}
				} else {
					out.println("You don't have the bulb.");
				}
				
			} else if (noun.equals("self ")) {		// if noun is self -- ENDING 2: easy way out
				out.println("\n\nYou kill yourself with " + noun2 + ".");
				out.println("\nThat's one way to escape.");
				out.println("\n\nFIN.");
				System.exit(0);
			} 
			
			} else {		// if noun2 is invalid
				out.println("\n\nYou can't attack " + noun + " with " + noun2 + ".");
			} 
		}
		
		return bulb_present;
	}
	
	// reads object -- paper only
	public static boolean read(String noun, boolean paper_read, boolean paper, boolean paper_present) {
		boolean boo1 = false;
	
		if (noun.equals("paper ")) {		// checks if noun is paper or not
			boo1 = true;
		} else {
			out.println("\n\nYou can't read " + noun + ".");
		}
		
		if (paper_present == true) {		// if the paper is present in room
		
			if (boo1 == true && paper == true) {		// if noun is paper and paper is in inventory
				out.println("\n\nYou read the papers tiny words.\n"
						+ "It reads...\n"
						+ "\nJust try opening the safe m8.");
				paper_read = true;		// paper is read -- safe can be accessed
			} else if (boo1 == true && paper == false) {
				out.println("\n\nYou need to pick up the paper first.");
			}
		
		} else {		// if no paper is present
			out.println("\n\nThere is no paper present.");
		}
		
		
		return paper_read;
	}
	
}