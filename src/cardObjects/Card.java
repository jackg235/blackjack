package cardObjects;

public class Card {
    private String value;
    private String suit;
    
    public Card(String value, String suit) {
        this.suit = suit;
        this.value = value;
    }

    public String getValue() {     
        return value;
    }
    
    public int getBlackjackScore() { 
        
        if (value.equals("A")) {
            return 1;      
        }
        
        if (value.equals("J") || value.equals("Q") || value.equals("K")) {
            return 10;
        }

        return Integer.parseInt(value);    
    }

    public boolean isAce() {   
        return value.equals("A");
    }
    
    public String toString() {
        
        return value + " of " + suit;
    }

}