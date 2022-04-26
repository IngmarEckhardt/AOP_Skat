package de.htwk.aop.skat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputImpl implements UserInput {
	private BufferedReader bufferedReader;

	public UserInputImpl() {
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public String getString(String message) {
		String input = "";

		System.out.print(message);

		try {
			input = bufferedReader.readLine();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		return input;
	}

	@Override
	public Integer getInt(String message) {

		Integer parsedInt = null;
		do {
			try {
				parsedInt = Integer.valueOf(getString(message));
			} catch (NumberFormatException ex) {
				System.out.println("Bitte geben Sie nur ganze Zahlen ein.");
			}
		} while (parsedInt==null);
		return parsedInt;
	}
	
	@Override
	public boolean jaNeinQuestion(String message) {
		while (true) {
			String inputString = getString(message);
			if (inputString.toUpperCase().startsWith("J")) {
				return true;
			} else if (inputString.toUpperCase().startsWith("N")) {
				return false;
			} else {
				System.out.println("Bitte wählen Sie mit j oder n");
			}
		}
	}
}