package cardObjects;

public interface Hand {
    /**
     * Add a card to your hand.
     * @param card - a HW8Card to be added to the hand.
     */
    public void addCard(Card card);
    
    /**
     * Get the score of the Blackjack hand if all aces are
     * treated as worth 1 point.
     */
    public int getHardScore();
      
    /**
     * Get the score of the Blackjack hand if the first ace
     * and only the first ace is worth 11 points instead of
     * 1. If there is no ace, this should give the same score
     * as getHardScore()
     * @return
     */
    public int getSoftScore();
    
     /**
     * Get the BEST blackjack score for the hand
     * that is as close to 21 as possible but not over
     * @return the best possible blackjack score
     */
    public int getScore();
    
    /**
     * return true if the hand is "bust" (all possible scores
     * are over 21)
     * @return boolean for whether or not hand is bust
     */
    public boolean isBust();
    
    /**
     * Return the number of cards in your Hand
     * @return int
     */
    public int getNumCards();
    
    
    /**
     * Return the second card in this hand. You don't need
     * to confirm this hand belongs to the dealer. Just return
     * the second card in the hand.
     * 
     * If the hand has fewer than two cards, throw an appropriate
     * exception.
     * 
     * @return HW8Card - the second card in the hand
     */
    public Card getDealerShowing();
    
    /**
     * Returns a string showing all the cards in the hand. Example:
     * "K of Spades, A of Clubs,  of Diamonds, 3 of Hearts"
     * 
     * The string must be formatted exactly as above.
     * Note that there should be no comma+whitespace after the 
     * LAST card, but there should be a comma+whitespace
     * between each card
     * @return
     */
    public String toString();
}
