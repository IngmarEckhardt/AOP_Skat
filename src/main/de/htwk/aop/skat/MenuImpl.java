package de.htwk.aop.skat;

class MenuImpl implements Menu {
	private boolean exit;

	private UserInput userInput;
	

	public MenuImpl() {
		this.exit = false;
		this.userInput = new UserInputImpl();
	}

	public void startMenu() {

		do {
			printMenu();
			chooseMenuEntry(userInput.getInt("Bitte waehlen Sie einen Menuepunkt"));
		} while (!exit);
	}

	private void printMenu() {
		System.out.println("\t\t\t\tMenu\n" + "\tSkat \t\t\t(1)\n" + "\tExit \t\t\t(0)");
	}

	private Object chooseMenuEntry(Integer menuChoice) {

		switch (menuChoice) {
		case 0:
			exit = true;
			return menuChoice;
		case 1:
			GameMaster gameMaster = new GameMaster();
			gameMaster.startGame();
		default:
			System.out.println("Bitte wählen Sie nur aus den vorgegebenen Menüpunkten");
			return null;
		}
	}
}