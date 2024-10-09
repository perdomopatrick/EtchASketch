package ui.menu;

import model.Canvas;

public class DrawMenu extends Menu{
	
	public DrawMenu() {
		quit = false;
	}
	
	public void menu() {
		String command;
		quit = false;
		do {
			prompt();
			command = scanner.nextLine().trim();
			evaluateStrInput(command);
		} while (!quit);
	}

	public void prompt() {
		Canvas canvas = gallery.getCurrCanvas();
		displayCanvasWithStylus(canvas.getBoard(), canvas.getStylusXCoord(), canvas.getStylusYCoord());
		System.out.println("\nStylus Coords: ("+canvas.getStylusXCoord()+","+canvas.getStylusYCoord()+")");
		System.out.println("\n=== Draw Menu ===");
		System.out.println("- Direction and Length");
		System.out.println("- Shake (shake, s)");
		System.out.println("- Quit (quit, q)");
		System.out.print("Choose an option: ");
	}

	protected void evaluateStrInput(String command) {
		int inputInt;
		String inputStr;
		if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q")) {
			System.out.println("Exiting the program.");
			quit = true; 
		} else if (command.equalsIgnoreCase("shake") || command.equalsIgnoreCase("s")) {
			shake();
		} else {
			try {
				String[] parts = command.split(" ");
				inputStr = parts[0]; 
				inputInt = Integer.parseInt(parts[1]);
				play(inputStr, inputInt);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input! The first input must be an String.");
			} catch (IndexOutOfBoundsException  e) {
				System.out.println("Invalid input! Input must be an String and Int to draw");
			}
		}
	}

	public void play(String direction, int length) {
		Canvas canvas = gallery.getCurrCanvas();		
		try {
			canvas.draw(direction.toLowerCase(), length);
		} catch (IndexOutOfBoundsException  e) {
			System.out.println("Invalid input! Input must be an Inbounds");
		}
	}

	private void shake() {
		System.out.println("Shaking...");
		Canvas canvas = gallery.getCurrCanvas();
		canvas.shake();
	}
}
