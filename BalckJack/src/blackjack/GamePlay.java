package blackjack;

import java.util.Scanner;

public class GamePlay {
	DeckOfCards deck;
	private int amountOfDecks;
	private Card[] playerCards;
	private Card[] casinoCards;
	public GamePlay() {
	
	}
	
	public void startGame() {
		System.out.println("How many deck of cards you want to play?");
		Scanner sc = new Scanner(System.in);
		amountOfDecks = sc.nextInt();
		deck = new DeckOfCards(amountOfDecks);
		deck.printAllCards();
		Card[] playerCards = deck.dealTheCards(4);
		showCards(playerCards);
		deck.printAllCards();
		playerCards = hit(playerCards);
		showCards(playerCards);
		deck.printAllCards();

	}
	
	
	public Card[] dealCards() {
		return deck.dealTheCards(2);
	}
	
	public void showCards(Card[] cards) {
		for (int i=0; i<cards.length; i++) {
			System.out.print(cards[i].getCardName() + " ");
		}
		System.out.println();
	}
	
	public Card[] hit(Card[] cards) {
		Card[] tempArr = new Card[cards.length];
		for (int i=0; i<tempArr.length; i++) {
			tempArr[i] = cards[i];
		}
		cards = new Card[tempArr.length +1];
		for (int i=0; i<tempArr.length; i++) {
			cards[i] = tempArr[i];
		}
		tempArr = deck.dealTheCards(1);
		cards[cards.length - 1] = tempArr[0];
		return cards;
	}
}
