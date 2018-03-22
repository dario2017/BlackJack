package blackjack;
 
public class BlackJackPlayer {
    private Card[] cards;
   
    public BlackJackPlayer() {
       
    }
   
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
   
    public Card[] getCards() {
        return this.cards;
    }
    public Card getCard(int numberOfCard) {
        return this.cards[numberOfCard];
    }
   
    public void showCards() {
        for (int i=0; i<cards.length; i++) {
            System.out.print(cards[i].getCardName() + " ");
        }
        System.out.println();
    }
   
    public int countScore() {
        int sum = 0;
        for (int i=0; i<cards.length; i++) {
            if (!(cards[i].getCardName().equals("Ace"))) {
                sum += cards[i].getCardValue();
            }
        }
        for (int i=0; i<cards.length; i++) {
            if (cards[i].getCardName().equals("Ace")) {
                if (sum + 11 > 21) {
                    sum += 1;
                } else {
                    sum += 11;
                }
            }
        }
        return sum;
    }
    

}