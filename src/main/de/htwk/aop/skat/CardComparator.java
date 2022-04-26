package de.htwk.aop.skat;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

	@Override
	public int compare(Card card1, Card card2) {
		switch (card1.getFarbe()) {
			case "Kreuz": {
				switch (card2.getFarbe()) {
					case "Kreuz":
						return compareValue(card1, card2);
					case "Pik", "Herz", "Karo":
						return 1;
						
					
				}
			}
			case "Pik": {
				switch (card2.getFarbe()) {
					case "Kreuz": 
						return -1;
					case "Pik":
						return compareValue(card1, card2);
					case "Herz", "Karo": 
						return 1;
				}
			
			}
			case "Herz": {
				switch (card2.getFarbe()) {
					case "Kreuz", "Pik": 
						return -1;
					case "Herz":
						return compareValue(card1, card2);
					case "Karo": 
						return 1;
				}
			}
			case "Karo": {
				switch (card2.getFarbe()) {
					case "Kreuz", "Pik", "Herz": 
						return -1;
					case "Karo":
						return compareValue(card1, card2);
				}
			}
			default:
				System.err.println("card1.getFarbe had the value" + card1.getFarbe());
				return 0;
		}
	}

	private int compareValue(Card card1, Card card2) {
		switch (card1.getWert()) {
			case "Ass":
				return 1;
			case "König": {
				switch (card2.getWert()) {
				case "Ass": 
					return -1;
				case "Dame", "Bube", "10", "9", "8", "7":
					return 1;
				default:
					System.err.println("card2.getWert was" + card2.getWert());
					return 0;
				}
			}
			
			case "Dame": {
				switch (card2.getWert()) {
				case "Ass", "König":
					return -1;
				case "Bube", "10", "9", "8", "7":
					return 1;
				default:
					System.err.println("card2.getWert was" + card2.getWert());
					return 0;
				}
			}
			case "Bube": {
				switch (card2.getWert()) {
				case "Ass", "König", "Dame":
					return -1;
				case "10", "9", "8", "7":
					return 1;
				default:
					System.err.println("card2.getWert was" + card2.getWert());
					return 0;
				}
			}
			case "10": {
				switch (card2.getWert()) {
				case "Ass", "König", "Dame", "Bube":
					return -1;
				case "9", "8", "7":
					return 1;
				default:
					System.err.println("card2.getWert was" + card2.getWert());
					return 0;
				}
			}
			case "9": {
				switch (card2.getWert()) {
				case "Ass", "König", "Dame", "Bube", "10":
					return -1;
				case "8", "7":
					return 1;
				default:
					System.err.println("card2.getWert was" + card2.getWert());
					return 0;
				}
			}
			case "8": {
				switch (card2.getWert()) {
				case "Ass", "König", "Dame", "Bube", "10", "9":
					return -1;
				case "7":
					return 1;
				default:
					System.err.println("card2.getWert was" + card2.getWert());
					return 0;
				}
			}
			case "7": {
				switch (card2.getWert()) {
				case "Ass", "König", "Dame", "Bube", "10", "9", "8":
					return -1;
				default:
					System.err.println("card2.getWert was" + card2.getWert());
					return 0;
				}
			}
			default:
				System.err.println("card1.getWert was " + card1.getWert());
				return 0;
		}

	}
}