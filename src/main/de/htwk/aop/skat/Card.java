package de.htwk.aop.skat;

public class Card {
	private static CardComparator cardComparator = new CardComparator();
	private String farbe, wert;

	public String getFarbe() {
		return farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

	public String getWert() {
		return wert;
	}

	public void setWert(String wert) {
		this.wert = wert;
	}

	@Override
	public String toString() {
		return farbe + " " + wert;
	}
	
	public boolean hasSameColorAs(Card cardToCompare) {
		if (this.farbe.equals(cardToCompare.getFarbe())) {return true;}
		return false;
	}
	
	public boolean hasHigherValueAs(Card cardToCompare) {
		if (cardComparator.compare(this, cardToCompare) > 0) {
			return true;
		}
		if (cardComparator.compare(this, cardToCompare) == 0) {
			System.err.println("Cards had the same value, this card has the value " + this.wert + "and cardToCompare " + cardToCompare.wert);
			return false;
		}
		return false;
	}
}