import java.util.Scanner;

import cardObjects.Deck;

public class Play {

    private static Scanner sc;
    private static int numTrials = 50;
    static int numWins = 0;
    static int numTies = 0;
    static int count = 0;
    public static int net = 0;

    public static void main(String[] args) {
        
        sc = new Scanner(System.in);
        
        int numDecks = getNumDecks("How many decks would you like to play with?");
        while (numDecks == -1) {
            numDecks = getNumDecks("How many decks would you like to play with?");
        }

        Game game = new Game(numDecks, sc);
        for (int i = 0; i < numTrials; i++) {
            
            Deck deck = new Deck(numDecks);
            deck.resetDeck();
            deck.shuffle();
            while (deck.deckSize() > 10) {
                System.out.println(deck.deckSize());
                System.out.println();
                count++;
                if (game.playRound(deck)) {
                    numWins++;
                }
            }
        }
   
        System.out.println("You won " + numWins + " hands out of " + count + " with " + numTies + " ties.");
        System.out.println("Your net is $" + net);
    }

    public static boolean isInt(String in) {
        try {
            Integer.parseInt(in);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static int getNumDecks(String prompt) {
        System.out.println(prompt);
        String input = sc.next();
        if (!isInt(input)) {
            System.out.println("Please use an integer value for your desired number of decks");
            return -1;
        }
        return Integer.parseInt(input);
    }

    public static boolean playAgain(String prompt) {
        return count <= numTrials;
    }

}