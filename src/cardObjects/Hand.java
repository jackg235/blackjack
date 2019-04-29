package cardObjects;

import java.util.ArrayList;

public class Hand {
    ArrayList<Card> hand = new ArrayList<Card>();
    private int hardScore;
    private int softScore;
    private int bestScore;
    private String theHand;


    public void addCard(Card card) {
        hand.add(card);
    }

    public int getHardScore() {

        hardScore = 0;

        for (Card cards : hand) {
            hardScore += cards.getBlackjackScore();
        }

        return hardScore;
    }

    public int getSoftScore() {

        softScore = getHardScore();
        boolean ace = false;

        for (Card cards : hand) {
            if (cards.isAce()) {
                ace = true;
            }
        }
        
        if (ace) {
            softScore += 10;
        }

        return softScore;

    }

    public int getScore() {
        bestScore = 0;
        if (getSoftScore() <= 21) {

            bestScore = getSoftScore();

        } else {

            bestScore = getHardScore();
        }

        return bestScore;
    }

    public boolean isBust() {

        return getScore() > 21;
    }

    public int getNumCards() {
        return hand.size();
    }

    public Card getDealerShowing() {
        Card card = hand.get(1);
        return card;
    }

    public String toString() {

        theHand = "";
        int position = 1;

        for (Card cards : hand) {

            if (position < hand.size()) {

                theHand += cards.toString() + ", ";
                position++;

            } else {
                theHand += cards.toString();
                break;
            }
        }
        return theHand;
    }
}
