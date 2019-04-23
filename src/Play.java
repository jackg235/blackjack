import java.util.Scanner;

public class Play {

    private static Scanner sc;

    public static void main(String[] args) {

        sc = new Scanner(System.in);
        boolean play = false;
        do {
            play = playAgain("Would you like to play Blackjack? (y/n)");
            if (play) {
                int numDecks = getNumDecks("How many decks would you like to play with?");
                while (numDecks == -1) {
                    numDecks = getNumDecks("How many decks would you like to play with?");
                }
                Game game = new Game(numDecks, sc);
                game.playRound();
            }
        } while (play);
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
        while (true) {
            System.out.println(prompt);
            String input = sc.next();
            if (input.toLowerCase().startsWith("y")) {

                return true;
            } else if (input.toLowerCase().startsWith("n")) {
                return false;
            }
            System.out.println("Invalid entry. Please enter yes or no.");
        }
    }

}
