import java.util.Scanner;

public class Play {

    private static Scanner sc;
    private static int numTrials = 100;
    private static int numWins = 0;
    private static int count = 0;
    public static int net = 0;

    public static void main(String[] args) {
        
        sc = new Scanner(System.in);
        
        int numDecks = getNumDecks("How many decks would you like to play with?");
        while (numDecks == -1) {
            numDecks = getNumDecks("How many decks would you like to play with?");
        }
        
        boolean play = false;
        do {
            play = playAgain("");
            if (play) {
                count++;
                Game game = new Game(numDecks, sc);
                if (game.playRound()) {
                    numWins++;
                }
                
                if (count % 10 == 0) {
                    System.out.println(count);
                }
            }
        } while (play);
        
        System.out.println("You won " + numWins + " games out of " + numTrials + ".");
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
