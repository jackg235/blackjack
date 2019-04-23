package cardObjects;

public interface Card {
    /**
     * Get the suit of the card. The suit is a string. It must be any of
     * "Spades", "Hearts", "Clubs", or "Diamonds"
     * @return the String value of suit.
     */
    public String getSuit();
    
    /**
     * Gets the value of the card as a string. It must be any of:
     * "2","3",...,"10","J',"Q","K","A"
     * @return
     */
    public String getValue();
    
    /**
     * Get the blackjack score of each card. All number cards are
     * worth their number. All face cards are worth 10.
     * Aces are worth 1 point.
     * @return an integer score for the card
     */
    public int getBlackjackScore();
    
    /**
     * Is the card an ace?
     * @return true if card is an ace, false otherwise
     */
    public boolean isAce();
    
    /**
     * returns true if o is a HW8Card and it's value and suit are
     * the same.  Example, the 8 of Spades is the same as
     * another 8 of Spades. However, the Q of Hearts is not the same
     * as the Q of Clubs.
     * @param o - the object to be compared
     * @return true if the VALUE and the SUIT of the cards are the same.
     */
    public boolean equals(Object o);
    
    /**
     * returns the card as a string of the form:
     * "[Value] of [Suit]"
     * Example: "A of Spades", "2 of Clubs"
     * @return a string representation of the HW8Card
     */
    public String toString();
}
