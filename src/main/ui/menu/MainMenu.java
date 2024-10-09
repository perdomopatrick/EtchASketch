package ui.menu;

public class MainMenu extends Menu{

	private final ViewMenu viewMenu;
	private final NewCanvasMenu newCanvasMenu;

	public MainMenu() {
		viewMenu = new ViewMenu();
		newCanvasMenu = new NewCanvasMenu();
		quit = false;
	}

	public void menu() {
		String choice;
		do {
			choice = getInputStr("new", "n", "view", "v", "quit", "q");
			evaluateStrInput(choice);
		} while(!quit);
	}
	
	public void prompt() {
		System.out.println("=== Main Menu ===");
		System.out.println("- New cavnas (new, n)");
		System.out.println("- View cavnas (view, v)");
		System.out.println("- Quit (quit, q)");
		System.out.print("Choose an option: ");
	}

	protected void evaluateStrInput(String command) {
		if (command.equalsIgnoreCase("new") || command.equalsIgnoreCase("n")) {
			newCanvasMenu.menu();
		} else if (command.equalsIgnoreCase("view") || command.equalsIgnoreCase("v")) {
			viewMenu.menu();
		} else if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q")) {
			System.out.println("Exiting...");
			quit = true; 
		} 
	}
}
