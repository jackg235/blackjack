package cardObjects;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private String suit;
    private int numDecks;

    ArrayList<Card> deck = new ArrayList<Card>();

    public Deck(int numDecks) {
        this.numDecks = numDecks;
    }

    public void resetDeck() {

        deck.clear();
        for (int j = 0; j < numDecks; j++) {
            for (int i = 0; i < 52; i++) {

                if (i < 13) {
                    suit = "Hearts";
                } else if (i < 26) {
                    suit = "Diamonds";
                } else if (i < 39) {
                    suit = "Spades";
                } else if (i < 52) {
                    suit = "Clubs";
                }

                if (i % 13 == 0) {
                    deck.add(new Card("A", suit));
                } else if (i % 13 == 10) {
                    deck.add(new Card("J", suit));
                } else if (i % 13 == 11) {
                    deck.add(new Card("Q", suit));
                } else if (i % 13 == 12) {
                    deck.add(new Card("K", suit));
                }

                else {
                    deck.add(new Card(Integer.toString(i % 13 + 1), suit));
                }
            }
        }
    }

    public void shuffle() {
        resetDeck();
        Collections.shuffle(deck);
    }

    public Card drawCard() {

        if (deck.size() > 0) {
            Card draw = deck.get(0);
            deck.remove(0);

            return draw;
        } else {
            throw new IllegalStateException("ERROR: deck is empty");
        }
    }

    public String toString() {
        return Integer.toString(deck.size());

    }
}
