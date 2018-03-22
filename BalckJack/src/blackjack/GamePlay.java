package blackjack;
 
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;
 
public class GamePlay {
    DeckOfCards deck;
    private int amountOfDecks;
    private BlackJackPlayer player;
    private BlackJackPlayer casino;
    private String choice;
    private boolean isGameActive = true;
    private int round = 0;
   
   
    public GamePlay() {
   
    }
   
    public void startGame() {
        System.out.println("How many deck of cards you want to play?");
        Scanner sc = new Scanner(System.in);
        amountOfDecks = sc.nextInt();
        deck = new DeckOfCards(amountOfDecks);
        while (true) {
        	if (round > 0) {
        		System.out.println("Are you ready for another round? If you want to quit playing type 'no'");
        		choice = sc.nextLine();
        		if (choice.equalsIgnoreCase("no")) {
        			System.out.println("Have a good day. Make more money and come back here again :) ");
        			break;
        		}
        	}
	        System.out.println("Croupier is dealing cards");
	        deck.mixDeckOfCards();
	//        deck.printAllCards();
	        player = new BlackJackPlayer();
	        casino = new BlackJackPlayer();
	        player.setCards(dealCards(2));
	        casino.setCards(dealCards(2));
	        System.out.print("Your cards: ");
	        player.showCards();
	        System.out.println("Your score: " + player.countScore());
	        System.out.println("Casino's cards: " + casino.getCard(0).getCardName() + " X");
	        System.out.println("What do you want to do? Type \"hit\" to get another card or type \"stand\" to stand");
	        while (true) {
	            choice = sc.nextLine();
	            if (choice.equalsIgnoreCase("hit")) {
	            	hitTheCard(player);
	                System.out.print("Your cards: ");
	                player.showCards();
	                System.out.println("Your score: " + player.countScore());
	                if (player.countScore() > 21) {
	                    System.out.println("Your score is " + player.countScore() + ", you lost");
	                    isGameActive = false;
	                    break;
	                }
	                System.out.println("Casino's cards: " + casino.getCard(0).getCardName() + " X");
	                System.out.println("What do you want to do? Type \"hit\" to get another card or type \"stand\" to stand");
	            } else if (choice.equalsIgnoreCase("stand")) {
	                break;
	            } else {
	                System.out.println("hit or stand?");
	            }
	        }
	        if (isGameActive) {
	        	showAllPlayersCards();
	            while (true) {
		            if (casino.countScore() > 21) {
		            	System.out.println("Casino has over 21. PLAYER WINS!!");
		            	isGameActive = false;
		            	break;
		            } else if (casino.countScore() <= 16) {
		            	hitTheCard(casino);
		            	System.out.println("Casino is taking new card...");
		            	showAllPlayersCards();
		            } else {
		            	whoWins();
		            	break;
		            }
	            }
	        }
	        round++;
	        isGameActive = true;
        }
    }
    
//    compares player's and casino's scores
    public BlackJackPlayer whoWins() {
    	System.out.println("Checking scores.....");
    	System.out.println("Player's score: " + player.countScore());
        System.out.println("Casino's score: " + casino.countScore());
    	if (casino.countScore() > player.countScore()) {
    		System.out.println("CASINO WINS!!");
    		return casino;
    	} else if (casino.countScore() < player.countScore()) {
    		System.out.println("PLAYER WINS!!!");
    		return player;
    	} else {
    		System.out.println("We've got a tie!");
    		return null;
    	}
    }
    
    public void showAllPlayersCards() {
    	System.out.print("Player's cards: ");
        player.showCards();
        System.out.print("Casino's cards: ");
        casino.showCards();
    }
//    adds another card to players deck
    public BlackJackPlayer hitTheCard(BlackJackPlayer player) {
    	player.setCards(hit(player.getCards()));
    	return player;
    }
        
    public Card[] dealCards(int numberOfCards) {
        return deck.dealTheCards(numberOfCards);
    }
   
//    returns array of cards with added new card from the deck of cards.
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