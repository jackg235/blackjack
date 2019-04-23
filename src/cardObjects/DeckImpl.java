package cardObjects;

import java.util.ArrayList;
import java.util.Collections;

public class DeckImpl implements Deck {

    private String suit;
    private int numDecks;

    // create deck of cards
    ArrayList<Card> deck = new ArrayList<Card>();

    public DeckImpl(int numDecks) {
        this.numDecks = numDecks;
    }

    /*
     * Description: resets deck to 52 unshuffled cards by clearing deck and adding
     * in order from A to K by suit. Input: n/a Output: n/a
     */
    public void resetDeck() {

        deck.clear();
        for (int j = 0; j < numDecks; j++) {
            for (int i = 0; i < 52; i++) {
                // determine the suit of the card
                if (i < 13) {
                    suit = "Hearts";
                } else if (i < 26) {
                    suit = "Diamonds";
                } else if (i < 39) {
                    suit = "Spades";
                } else if (i < 52) {
                    suit = "Clubs";
                }
                // check if card is a face card or an ace
                if (i % 13 == 0) {
                    deck.add(new CardImpl("A", suit));
                } else if (i % 13 == 10) {
                    deck.add(new CardImpl("J", suit));
                } else if (i % 13 == 11) {
                    deck.add(new CardImpl("Q", suit));
                } else if (i % 13 == 12) {
                    deck.add(new CardImpl("K", suit));
                }
                // for cards 2-10
                else {
                    deck.add(new CardImpl(Integer.toString(i % 13 + 1), suit));
                }
            }
        }
    }

    /*
     * Description: calls the reset function and then shuffles the deck Input: n/a
     * Output: n/a
     */
    public void shuffle() {
        resetDeck();
        Collections.shuffle(deck);
    }

    /*
     * Description: draws the first card in the deck and then removes it Input: n/a
     * Output: HW8Card (index 0)
     */
    public Card drawCard() {

        // check if there are cards still in deck, draw and remove first card
        if (deck.size() > 0) {
            Card draw = deck.get(0);
            deck.remove(0);

            return draw;
        } else {
            throw new IllegalStateException("ERROR: deck is empty");
        }
    }

    /*
     * Description: returns number of cards left in deck Input: n/a Output: number
     * of cards as a string
     */
    public String toString() {
        return Integer.toString(deck.size());

    }

}
