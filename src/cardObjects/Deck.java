package cardObjects;

public interface Deck {
  //The deck constructor should have no arguments
    //This object should contain a List<HW8Card>
    
    
    /**
     * This "resets" the deck to having 52 cards. It should
     * have for each value, one card of each suit.
     * Example, it should contain a "2 of Spades", "2 of Hearts"
     * "2 of Clubs", and "2 of Diamonds", then the same for
     * 3, 4, 5...,J,Q,K,A.
     * 
     * This should NOT shuffle the deck.
     */
    public void resetDeck();
    
    /**
     * Randomizes the order of the deck.
     * Use the static Collections.shuffle(List l) function
     */
    public void shuffle();
    
    /**
     * Remove one HW8Card form the deck and return it.
     * @return the HW8Card that was removed form the deck
     */
    public Card drawCard();
    
    /**
     * Return a string representation of how many cards are
     * in the deck.
     * Example: "Deck has 52 card(s)"
     * 
     * @return a string
     */
    public String toString();
}
