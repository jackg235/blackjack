package cardObjects;

import java.util.ArrayList;

public class HandImpl implements Hand {

    ArrayList<Card> hand = new ArrayList<Card>();
    private int hardScore;
    private int softScore;
    private int bestScore;
    private String theHand;

    /*
     * Description: Adds a card object to the hand Input: HW8 card Output: n/a
     */
    public void addCard(Card card) {

        hand.add(card);

    }

    /*
     * Description: Gets the blackjack score of the hand with all aces treated as 1
     * point Input: n/a Output: int hardScore
     */
    public int getHardScore() {

        hardScore = 0;

        for (Card cards : hand) {
            hardScore += cards.getBlackjackScore();
        }

        return hardScore;
    }

    /*
     * Description: Gets the blackjack score of the hand with the FIRST ace treated
     * as 11.If there are no aces, gives same result as getHardScore Input: n/a
     * Output: int softScore
     */
    public int getSoftScore() {

        softScore = getHardScore();
        boolean ace = false;

        for (Card cards : hand) {
            if (cards.isAce() == true) {
                ace = true;
            }
        }
        if (ace == true) {
            softScore += 10;
        }

        return softScore;

    }

    /*
     * Description: Returns the best score possible for the hand (between Soft and
     * Hard score) Input: n/a Output: int bestScore
     */
    public int getScore() {
        bestScore = 0;
        if (getSoftScore() <= 21) {

            bestScore = getSoftScore();

        } else {

            bestScore = getHardScore();
        }

        return bestScore;
    }

    /*
     * Description: checks to see if the player has busted, or had scored greater
     * than 21 Input: n/a Output: true if all possible scores are above 21, false
     * otherwise
     */
    public boolean isBust() {

        if (getScore() > 21) {

            return true;

        }
        return false;

    }

    /*
     * Description: Return the number of cards in the hand Input: n/a Output:
     * hand.size()
     */
    public int getNumCards() {

        return hand.size();

    }

    /*
     * Description: functions will return the card that the dealer is showing Input:
     * n/a Output: Return the SECOND (index 1) card in the hand.
     */
    public Card getDealerShowing() {

        Card card = hand.get(1);
        return card;
    }

    /*
     * Description: Prints the hand (see below for details) Input: n/a Output:
     * String cards
     */
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
