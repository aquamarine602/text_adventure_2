package cspik3_text;

/**
 * CURRENT BUGS:
 * you can attack yourself with nouns that you don't have
 * bulb_present is false?
 */

import java.util.Scanner;
import static java.lang.System.out;

public class Game {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		game(input);	// plays method 'game'
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
		String location = room_center(lamp, box, bulb_present);
		
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
		
		// tutorial	-- asks if user wants tutorial and gives tutorial
		boolean tutorial = tutorial(input);
		if (tutorial == true) {
			out.println("\n\nUse 'look' to study your surroundings.\n"
					+ "Use 'take (object)' to add an object to your inventory.\n"
					+ "Use 'open (object)' to open an object.\n"
					+ "Use 'go (direction)' to move.\n"
					+ "Use 'attack (object)' to attack or destroy an object.\n"
					+ "Use 'read (object)' to read an object.");
		}
		
		while (true) {
			// user types in verb and possible noun
			out.print("\n\n>");
			String words = input.nextLine();
			words = words + " " + " ";		// makes sure noun can be empty
				
			// gets index of spaces
			int space = words.indexOf(" ");
			int last_space = words.lastIndexOf(" ");
				
			// declares user's verb and noun
			String verb = words.substring(0, space);
			String noun = words.substring(space + 1, last_space);
			verb.toLowerCase();
			noun.toLowerCase();

			if (verb.equals("look")) {
				out.println(location);
				
			} else if (verb.equals("take")) {
				boolean boo1 = noun_check(noun);
				if (boo1 == true) {
					if (noun.equals("box ")) {
						box = take(input, noun, box, lamp, bulb, hammer, stool, paper, bulb_present, hammer_present, stool_present, paper_present);
					} else if (noun.equals("lamp ")) {
						lamp = take(input, noun, box, lamp, bulb, hammer, stool, paper, bulb_present, hammer_present, stool_present, paper_present);
					} else if (noun.equals("bulb ")) {
						bulb = take(input, noun, box, lamp, bulb, hammer, stool, paper, bulb_present, hammer_present, stool_present, paper_present);
					} else if (noun.equals("hammer ")) {
						hammer = take(input, noun, box, lamp, bulb, hammer, stool, paper, bulb_present, hammer_present, stool_present, paper_present);
					} else if (noun.equals("stool ")) {
						stool = take(input, noun, box, lamp, bulb, hammer, stool, paper, bulb_present, hammer_present, stool_present, paper_present);
					} else if (noun.equals("paper ")) {
						paper = take(input, noun, box, lamp, bulb, hammer, stool, paper, bulb_present, hammer_present, stool_present, paper_present);
					}
				}
				
			} else if (verb.equals("open")) {
				boolean boo1 = false;
				if (noun.equals("safe ")) {
					if (paper_read == true) {
						boo1 = true;
					} else {
						out.println("/n/nYou stare at the closed safe.\n"
								+ "Don't safes need passwords?");
					}
				} else {
					boo1 = noun_check(noun);
				}
				
				if (boo1 == true) {
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
				
			} else if (verb.equals("go")) {
				location = go(location, noun, box, lamp, bulb_present, stool_present, up);
				
			} else if (verb.equals("attack")) {
				bulb_present = attack(noun, bulb, bulb_present);
				
			} else if (verb.equals("read")) {
				paper_read = read(noun, paper_read, paper);
				
			} else {
				out.println("I don't know '" + verb + "'.");
			}
		}
		
	}
	
	public static boolean tutorial(Scanner input) {
		
		// asks if user wants the tutorial
		out.println("\n\nWould you like to hear the instructions?");
		out.print(">");
		String tutorial_ans = input.nextLine();
		tutorial_ans.toLowerCase();
		
		// checks to see if the computer should play tutorial
		boolean tutorial = false;
		
		// makes sure answer is valid
		while (true) {
			if (tutorial_ans.equals("yes") || tutorial_ans.equals("y") || tutorial_ans.equals("no") || tutorial_ans.equals("n")) {
				if (tutorial_ans.equals("yes") || tutorial_ans.equals("y")) {
					tutorial = true;
					break;
				} else {
					break;
				}
				
			} else {
				out.println("\n'" + tutorial_ans + "' is not a valid answer.\nTry yes/no or y/n.");
				out.print(">");
				tutorial_ans = input.nextLine();
				
			}
		}
		
		return tutorial;
	}

	
	public static boolean noun_check(String one) {
		boolean boo1 = true;
		
		if (one.equals("box ") || one.equals("lamp ") || one.equals("bulb ") || one.equals("stool ") || one.equals("paper ") || one.equals("hammer ")) {
		} else {
			out.println("\nI don't know the noun '" + one + "'.");
			boo1 = false;
		}
		
		return boo1;
		
	}
	
	public static String room_center(boolean lamp, boolean box, boolean bulb) {
		String words = "  _   _   _   _     _   _   _   _   _   _  \r\n" + 
				" / \\ / \\ / \\ / \\   / \\ / \\ / \\ / \\ / \\ / \\ \r\n" + 
				"( R | o | o | m ) ( C | e | n | t | e | r )\r\n" + 
				" \\_/ \\_/ \\_/ \\_/   \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ ";
		
		words = words + "\n\nYou are in the center of a cold\n"
				+ "and damp room. The shape of the room\n"
				+ "seems to be a perfect square,\n"
				+ "though the room itself isn't very\n"
				+ "large. There are no doors, there are\n"
				+ "no windows.\n\n";
		
		if (lamp == false) {
			words = words + 
				"There is a lamp dangling from the ceiling.\n"
				+ "The ceiling is crumbling around the hinges\n"
				+ "of the lamp.\n\n";
			
			if (bulb == false) {
				words = words +
						"Inside the lamp, there is a light bulb\n"
						+ "that is screwed in loosely.\n\n";
				
				words = words +
						" .=====.\r\n"
						+ "   |_|\r\n"
						+ "   | |\r\n"
						+ "(-------)\r\n"
						+ "(  (o)  )\n\n";
				
			} else {
				words = words +
						" .=====.\r\n"
						+ "   |_|\r\n"
						+ "   | |\r\n"
						+ "(-------)\r\n"
						+ "(       )\n\n";
			}
			
		}
		
		if (box == false) {
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
		
		words = words +
				"To the south is one wall of the room.\n"
				+ "There is a different colored tile\n"
				+ "on that wall.";
		
		return words;
		
	}
	
	public static String south_wall(boolean a) {
		return null;
	}
	
	public static String up(String a) {
		out.println("You're out.");
		System.exit(0);
		return null;
	}
	
	public static boolean take(Scanner input, String noun, boolean box, boolean lamp, boolean bulb, boolean hammer, boolean stool, boolean paper, boolean bulb_present, boolean hammer_present, boolean stool_present, boolean paper_present) {
		boolean taken = false;

		if (noun.equals("box ")) {
			if (box == true) {
				out.println("You already have " + noun + ".");
			} else {
				out.println("Taken");
				taken = true;
			}
				
		} else if (noun.equals("lamp ")) {
			if (lamp == true) {
				out.println("You already have " + noun + ".");
			} else {
				out.println("Taken");
				taken = true;
			}
				
		} else if (noun.equals("bulb ")) {
			if (bulb_present == false) {
				out.println("You have already broken" + noun + ".");
			} else {
				if (bulb == true) {
					out.println("You already have " + noun + ".");
				} else {
					out.println("Taken");
					taken = true;
				}
			}
		
		} else if (noun.equals("hammer ")) {
			if (hammer_present == false) {
				out.println("There is no " + noun + " present.");
			} else {
				if (hammer == true) {
					out.println("You already have " + noun + ".");
				} else {
					out.println("Taken");
					taken = true;
				}
			}
			
		} else if (noun.equals("stool ")) {
			if (stool_present == false) {
				out.println("There is no " + noun + " present.");
			} else {
				if (stool == true) {
					out.println("You already have " + noun + ".");
				} else {
					out.println("Taken");
					taken = true;
				}
			}
				
		} else if (noun.equals("paper ")) {
			if (paper_present == false) {
				out.println("There is no " + noun + " present.");
			} else {
				if (paper == true) {
					out.println("You already have " + noun + ".");
				} else {
					out.println("Taken");
					taken = true;
				}
			}
			
		}
		
		return taken;
	}
	
	public static boolean open(String noun, boolean box, boolean bulb, boolean paper_read, boolean stool_present, boolean hammer_present) {
		boolean returned = false;
		
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
				returned = true;
				
			} else if (noun.equals("box ")) {
				out.println("\n\nYou open the box. Inside the box\n"
						+ "is a hammer.\n\n"
						+ "       ,\r\n" + 
						"      /(  ___________\r\n" + 
						"     |  >:===========`\r\n" + 
						"      )(\r\n" + 
						"      \"\"\r\n");
				returned = true;
				
			}
		
		return returned;

	}
	
	public static String go(String location, String noun, boolean box, boolean lamp, boolean bulb_present, boolean stool_present, boolean up) {
		if (noun.equals("north ")) {
			out.println("\n\nYou go north to the room center.\n"
					+ "Farther north is an empty wall.");
			location = room_center(lamp, box, bulb_present);
		} else if (noun.equals("east ")){
			out.println("\n\nYou go to the east wall."
					+ "There is an empty wall here. It\n"
					+ "is covered in white tiles.");
			location = "\n\nThere is an empty wall here. It\n"
					+ "is covered in white tiles.";
			
		} else if (noun.equals("south ")) {
			out.println("\n\nYou go to the south wall.");
			location = south_wall(stool_present);
			
		} else if (noun.equals("west ")) {
			out.println("\n\nYou go to the west wall."
					+ "There is an empty wall here. It\n"
					+ "is covered in white tiles.");
			location = "\n\nThere is an empty wall here. It\n"
					+ "is covered in white tiles.";
			
		} else if (noun.equals("up ")) {
			if (up == true) {
				out.println("\n\nYou climb up onto the roof.");
				location = up("");
			} else {
				out.println("\n\nYou cannot go up.");
			}
		}
		
		return location;
	}
	
	public static boolean attack(String noun, boolean bulb_present, boolean bulb) {
		Scanner input = new Scanner(System.in);
		
		boolean boo1 = false;
		if (noun.equals("self")) {
			boo1 = true;
		} else {
			boo1 = noun_check(noun);
		}
		
		boolean boo2 = false;
		String noun2 = "";
		
		if (boo1 == true) {
			out.println("\nWhat are you attacking " + noun + " with?");
			out.print(">");
			noun2 = input.nextLine();
			noun2 = noun2 + " ";
			boo2 = noun_check(noun2);
			
			if (boo2 == true) {
				
			if (noun.equals("bulb ")) {
				if (bulb == true) {
					if (bulb_present == true) {
						if (noun2.equals("hammer ")) {
							out.println("\n\nThe bulb bursts as as the hammer\n"
									+ "comes in contact with it.\n"
									+ "A small leaf of paper flies out from\n"
									+ "where the bulb once was.");
							bulb_present = false;
						} else {
							out.println("\n\nTry attacking the bulb with the hammer.");
						}
					} else {
						out.println("The bulb is already broken.");
					}
				} else {
					out.println("You don't have the bulb.");
				}
				
			} else if (noun.equals("self")) {
				out.println("\n\nYou kill yourself with " + noun2 + ".");
				out.println("\nThat's one way to escape.");
				out.println("\n\nFIN.");
				System.exit(0);
			} else {
				out.println("\n\nYou can't attack " + noun + " with " + noun2 + ".");
			}
			
			}
		}
		
		return bulb_present;
	}
	
	public static boolean read(String noun, boolean paper_read, boolean paper) {
		boolean boo1 = false;
		if (noun.equals("paper ")) {
			boo1 = true;
		} else {
			out.println("\n\nYou can't read " + noun + ".");
		}
		
		if (boo1 == true && paper == true) {
			out.println("\n\nYou read the papers tiny words.\n"
					+ "It reads...\n"
					+ "\nJust try opening the safe m8.");
			paper_read = true;
		}
		return paper_read;
	}
	
	
	
	
	
	
	
	
}