package de.htwk.aop.skat;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private Card[] deck;
	private ArrayList<Card> actualDeck;
	private Random randomGenerator;
	
	public Deck () {
		this.deck = genCardDeck(new Card[32]);
		this.randomGenerator = new Random(System.nanoTime());
		mixDeck();
		actualDeck = new ArrayList<Card>();
		for (Card card : deck) {
			actualDeck.add(card);
		}
	}
	
	public Card drawCard () {
		if (actualDeck.isEmpty()) {
			return null;
		}
		return actualDeck.remove(0);
		
	}
	
	private static Card[] genCardDeck(Card[] deck) {
		final String farben[] = { "Kreuz", "Pik", "Herz", "Karo" };
		final String werte[] = { "Ass", "König", "Dame", "Bube", "10", "9", "8", "7" };
		int i = 0;
		for (String f : farben) {
			for (String w : werte) {
				deck[i] = new Card();
				deck[i].setFarbe(f);
				deck[i].setWert(w);
				i++;
			}
		}
		return deck;
	}

	public void mixDeck() {
		for (int i = 1; i < 1000; i++) {
			int index1 = randomGenerator.nextInt(32);
			int index2 = randomGenerator.nextInt(32);
			Card intermediateCard = deck[index1];
			deck[index1] = deck[index2];
			deck[index2] = intermediateCard;
		}

	}
}