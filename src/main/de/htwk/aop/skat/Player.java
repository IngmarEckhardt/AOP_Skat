package de.htwk.aop.skat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Player {
	private ArrayList<Card> personalDeck, trick;
	private String name;
	private static Comparator<Card> cardComparator = new CardComparator();
	private Integer playerId = 1;

	public Player(String name) {
		this.personalDeck = new ArrayList<Card>();
		this.trick = new ArrayList<Card>();
		this.name = name;
		this.playerId = playerId++;
	}

	public ArrayList<Card> getPersonalDeck() {
		return personalDeck;
	}

	public ArrayList<Card> getTrick() {
		return trick;
	}

	public String getName() {
		return name;
	}

	public void addToPersonalDeck(Card cardToAdd) {
		personalDeck.add(cardToAdd);
		personalDeck.sort(Collections.reverseOrder(cardComparator));
	}

	public String printDeck() {
		String deckAsString = "";
		for (int i = 0; i < personalDeck.size(); i++) {
			deckAsString = deckAsString + "(" + (i + 1) + ")" + personalDeck.get(i) + "\n";

		}
		return deckAsString;
	}

	public Card playCard(int index) throws IndexOutOfBoundsException {
		return personalDeck.remove(index);
	}

	public void AddToTrick(Card cardToAdd) {
		trick.add(cardToAdd);
	}

	public Integer getValueOfTrick() {
		int valueOfTrick = 0;
		if (trick == null) {
			return null;
		}

		for (Card card : trick) {
			switch (card.getWert()) {
			case "Ass":
				valueOfTrick += 11;
				break;
			case "10":
				valueOfTrick += 10;
				break;
			case "König":
				valueOfTrick += 4;
				break;
			case "Dame":
				valueOfTrick += 3;
				break;
			case "Bube":
				valueOfTrick += 2;
			}
		}
		return valueOfTrick;
	}
}