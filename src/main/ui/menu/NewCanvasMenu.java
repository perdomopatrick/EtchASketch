package ui.menu;

public class NewCanvasMenu extends Menu{
	private final DrawMenu drawMenu;
	private int height;
	private int width;

	public NewCanvasMenu() {
		drawMenu = new DrawMenu();
		quit = false;
		height = 0;
		width = 0;
	}
	@Override
	public void menu() {
		int[] dimensions = getDimensionsInput();
		int height = dimensions[0];
		int width = dimensions[1];
		System.out.println("Canvas created with Height: " + height + " and Width: " + width);
		gallery.newCanvas(width, height);
		drawMenu.menu();
	}

	public void prompt() {
		System.out.println("\n=== New Canvas ===");
		System.out.println("Please specify the dimensions of the canvas");
		System.out.print("Enter Height and Width (e.g., '10 20'): ");
	}

	protected void evaluateStrInput(String command) {
		if (command.matches("[1-9][0-9]* [1-9][0-9]*")) {
			String[] parts = command.split(" ");
			height = Integer.parseInt(parts[0]);
			width = Integer.parseInt(parts[1]);
			quit = true;
		} else {
			System.out.println("Invalid input. Please enter two positive integers separated by a space.");
		}
	}
	
	private int[] getDimensionsInput() {
		String command;
		do {
			prompt();
			command = scanner.nextLine().trim();
			evaluateStrInput(command);
		} while (!quit);
		return new int[]{height, width};
	}
}
