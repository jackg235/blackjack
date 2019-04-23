package cardObjects;

public class CardImpl implements Card {

    private String value;
    private String suit;
    
    /*
     * Description: constructor that checks to see if card input is valid
     * Input: String value & String suit
     * Output: n/a
     */
    public CardImpl(String value, String suit) {
        
        if (!suit.equals("Hearts") && !suit.equals("Spades") &&
            !suit.equals("Clubs") && !suit.equals("Diamonds")) {
            throw new IllegalArgumentException("ERROR: invalid suit");            
        }
        if (!value.equals("A") && !value.equals("2") && !value.equals("3") &&
            !value.equals("4") && !value.equals("5") && !value.equals("6") &&
            !value.equals("7") && !value.equals("8") && !value.equals("9") &&
            !value.equals("10") && !value.equals("J") && !value.equals("Q") &&
            !value.equals("K")) {
            throw new IllegalArgumentException("ERROR: invalid value");
        }
        this.suit = suit;
        this.value = value;
    }
    
    /*
     * Description: getter function to return suit of the card
     * Input: n/a
     * Output: String suit
     */
    public String getSuit() { 
        
        return suit;
    }
    
    /*
     * Description: getter function to return the value of the card
     * Input: n/a
     * Output: String value
     */
    public String getValue() {  
        
        return value;
    }
    
    /*
     * Description: returns the blackjack score of the card. Number cards
     * are worth their number. Face cards worth 10. Ace worth 1
     * Input: n/a
     * Output: int score
     */
    public int getBlackjackScore() { 
        
        //if ace
        if (value.equals("A")) {
            return 1;      
        }
        //if face card
        if (value.equals("J") || value.equals("Q") || value.equals("K")) {
            return 10;
        }
        //all other cards
        return Integer.parseInt(value);
        
    }
    
    /*
     * Description: returns true if card is ace, false otherwise
     * Input: n/a
     * Output: boolean true if ace, false otherwise
     */
    public boolean isAce() { 
        
        if (value.equals("A")) {
            return true;      
        }
        
        return false;
    }
    
    /*
     * Description: checks to see if 2 cards have the same value and suit
     * Input: Object o
     * Output: boolean true or boolean false if the card equals Object o
     */
    public boolean equals(Object o) { 
        
        try { 
            CardImpl other = (CardImpl) o;
            //check if 2 cards are the same
            return this.suit.equals(other.suit) && 
                this.value.equals(other.value);
        }
        
        //check if object cannot be cast to a card
        catch (ClassCastException e) {
            
            return false;
        }
    }
    
    /*
     * Description: prints card as a string 
     * Input: n/a
     * Output: String card
     */
    public String toString() {
        
        return value + " of " + suit;
    }

}
