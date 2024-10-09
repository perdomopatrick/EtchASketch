package ui.menu;

public class ViewMenu extends Menu{
	private final DrawMenu drawMenu;

	public ViewMenu() {
		drawMenu = new DrawMenu();
		quit = false;
	}

	public void menu() {
		String choice;
		gallery.resetCurrCanvas();
		do {
			viewCanvas();
			choice = getInputStr("next", "n", "prev", "p", "draw", "d", "quit", "q");
			evaluateStrInput(choice);
		} while(!quit);
	}

	public void prompt() {
		System.out.println("\n=== View Menu ===");
		System.out.println("Next cavnas (next, n)");
		System.out.println("Prev cavnas (prev, v)");
		System.out.println("Draw (draw, d)");
		System.out.println("Quit (quit, q)");
		System.out.print("Choose an option: ");
	}
	
	protected void evaluateStrInput(String command) {
		if (command.equalsIgnoreCase("next") || command.equalsIgnoreCase("n")) {
			next();
		} else if (command.equalsIgnoreCase("prev") || command.equalsIgnoreCase("p")) {
			prev();
		} else if (command.equalsIgnoreCase("draw") || command.equalsIgnoreCase("d")) {
			try  {
				drawMenu.menu();
			} catch (IndexOutOfBoundsException  e) {
				System.out.println("No canvas to draw on");
			}
		} else if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q")) {
			System.out.println("Exiting...");
			quit = true; 
		} else {
			System.out.println("Invalid choice. Please try again.");
		}
	}

	private void viewCanvas() {
		try  {
			displayCanvas(gallery.getCurrCanvas().getBoard());
		} catch (IndexOutOfBoundsException  e) {
			System.out.println("Invalid input! No canvas");
		}
	}

	private void next() {
		gallery.nextCanvas();
	}

	private void prev() {
		gallery.prevCanvas();
	}
}
