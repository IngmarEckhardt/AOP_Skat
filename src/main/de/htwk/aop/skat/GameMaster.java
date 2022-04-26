package de.htwk.aop.skat;

public class GameMaster {
	private Deck deck;
	private Card[] skat;
	private UserInput userInput;
	private Player playerOne, playerTwo, playerThree, winner;

	public GameMaster() {
		this.deck = new Deck();
		this.skat = new Card[2];
		this.userInput = new UserInputImpl();
		new CardComparator();
	}

	public void startGame() {
		askPlayersForTheirNames();
		distributeCards();
		printAllDecksAndSkat();

		for (int rounds = 1; rounds < 11; rounds++) {
			playRounds();
		}

		winner = getWinner();

		System.out.println("Der Gewinner ist " + winner.getName() + " mit " + winner.getValueOfTrick() + " Punkten");
	}


	private void askPlayersForTheirNames() {
		playerOne = new Player(userInput.getString("Bitte den Namen des ersten Spielers eingeben: "));
		playerTwo = new Player(userInput.getString("Bitte den Namen des zweiten Spielers eingeben: "));
		playerThree = new Player(userInput.getString("Bitte den Namen des dritten Spielers eingeben: "));
	}

	private void distributeCards() {
		for (int i = 0; i < 3; i++) {
			playerOne.addToPersonalDeck(deck.drawCard());
			playerTwo.addToPersonalDeck(deck.drawCard());
			playerThree.addToPersonalDeck(deck.drawCard());
		}
		skat[0] = deck.drawCard();
		skat[1] = deck.drawCard();

		for (int i = 0; i < 4; i++) {
			playerOne.addToPersonalDeck(deck.drawCard());
			playerTwo.addToPersonalDeck(deck.drawCard());
			playerThree.addToPersonalDeck(deck.drawCard());
		}
	}
	private void printAllDecksAndSkat() {
		System.out.println("Das Deck des ersten Spielers enthält \n" + playerOne.printDeck()
				+ "\nDas Deck des zweiten Spielers enthält \n" + playerTwo.printDeck()
				+ "\nDas Deck des dritten Spielers enthält \n" + playerThree.printDeck() + "\nDer Skat enhält "
				+ skat[0] + " und " + skat[1] + "\n");
	}

	private void playRounds() {
		Card playerOnesCard, playerTwosCard, playerThreesCard;
		Card[] trick;
		playerOnesCard = getCardForThisRound(playerOne, null);
		playerTwosCard = getCardForThisRound(playerTwo, playerOnesCard);
		playerThreesCard = getCardForThisRound(playerThree, playerOnesCard);
		trick = new Card[] {playerOnesCard,playerTwosCard,playerThreesCard};

		if (playerOnesCard.hasSameColorAs(playerTwosCard) && playerOnesCard.hasSameColorAs(playerThreesCard)) {

			if (playerOnesCard.hasHigherValueAs(playerTwosCard)) {

				if (playerOnesCard.hasHigherValueAs(playerThreesCard)) {
					giveAllCardsTo(playerOne, trick);
				} else {
					giveAllCardsTo(playerThree, trick);
				}

			} else {

				if (playerTwosCard.hasHigherValueAs(playerThreesCard)) {
					giveAllCardsTo(playerTwo, trick);
				} else {
					giveAllCardsTo(playerThree, trick);
				}
			}

		} else if (playerOnesCard.getFarbe().equals(playerTwosCard.getFarbe())) {

			if (playerOnesCard.hasHigherValueAs(playerTwosCard)) {
				giveAllCardsTo(playerOne, trick);
			} else {
				giveAllCardsTo(playerTwo, trick);
			}

		} else if (playerOnesCard.getFarbe().equals(playerThreesCard.getFarbe())) {

			if (playerOnesCard.hasHigherValueAs(playerThreesCard)) {
				giveAllCardsTo(playerOne, trick);
			} else {
				giveAllCardsTo(playerThree, trick);
			}

		} else {
			giveAllCardsTo(playerOne, trick);
		}
	}

	private Card getCardForThisRound(Player player, Card cardFromFirstPlayer) {
		Card thisPlayersCard = null;

		System.out.println(player.getName() + "'s Deck\n" + player.printDeck());

		try {
			thisPlayersCard = player
					.playCard(userInput.getInt("Welche Karte möchtest du spielen " + player.getName() + "?") - 1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Bitte wähle nur aus den vorhandenen Karten!");
			return getCardForThisRound(player, cardFromFirstPlayer);
		}

		if (!thisPlayersCard.hasSameColorAs(cardFromFirstPlayer) && cardFromFirstPlayer != null) {

			for (Card card : player.getPersonalDeck()) {
				if (card.hasSameColorAs(cardFromFirstPlayer)) {
					System.out.println("Du musst die Farbe des ersten Spielers bedienen!");
					player.addToPersonalDeck(thisPlayersCard);
					return getCardForThisRound(player, cardFromFirstPlayer);
				}
			}
		}

		return thisPlayersCard;
	}

	private Player getWinner() {
		if (playerOne.getValueOfTrick() > playerTwo.getValueOfTrick()) {
			if (playerOne.getValueOfTrick() > playerThree.getValueOfTrick()) {
				return playerOne;
			} else if (playerOne.getValueOfTrick() < playerThree.getValueOfTrick()) {
				return playerThree;
			} else {
				System.out.println("Spieler " + playerOne.getName() + " und Spieler " + playerThree.getName()
						+ "haben gleich viele Punkte");
			}

		} else if (playerOne.getValueOfTrick() < playerTwo.getValueOfTrick()) {
			if (playerTwo.getValueOfTrick() > playerThree.getValueOfTrick()) {
				return playerTwo;
			} else if (playerTwo.getValueOfTrick() < playerThree.getValueOfTrick()) {
				return playerThree;
			} else {
				System.out.println("Spieler " + playerTwo.getName() + " und Spieler " + playerThree.getName()
						+ "haben gleich viele Punkte");
			}

		}
		return null;
	}

	private void giveAllCardsTo(Player winner, Card[] trick) {
		System.out.println("Player " + winner.getName() + " gewann die Runde. ");
		for (Card card : trick) {
			winner.getTrick().add(card);
		}
	}
}