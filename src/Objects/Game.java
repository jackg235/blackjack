package Objects;
import java.util.Scanner;

import cardObjects.*;

public class Game {

    private Scanner sc;
    private int numDecks;
    
    public Game(int decks, Scanner sc) {
        this.numDecks = decks;
        this.sc = sc;
    }

    public boolean playRound() {
        Deck deck = new DeckImpl(numDecks);
        deck.shuffle();
        
        Hand playerHand = new HandImpl();
        Hand dealerHand = new HandImpl();
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());

        if (playerHand.getScore() == 21) {
            System.out.println("Blackjack! You win!");
            return true;
        }

        System.out.println("Your hand:" + playerHand);
        System.out.println("The dealer is showing: " + dealerHand.getDealerShowing());
        while (hitOrStay("Would you like to hit?")) {
            playerHand.addCard(deck.drawCard());
            if (playerHand.isBust()) {
                System.out.println("Your hand is: " + playerHand);
                System.out.println("You busted! You lose!");
                return false;
            }
            System.out.println("Your hand is: " + playerHand);
            if (playerHand.getNumCards() >= 5) {
                System.out.println("You have 5 cards and cannot hit again.");
                break;
            }
        }

        System.out.println("The Dealer's hand is: " + dealerHand);
        if (playerHand.getScore() < dealerHand.getScore()) {
            System.out.println("Your hand is worse than the dealer's. You lose.");
            return false;
        }

        while (dealerHit(dealerHand)) {
            System.out.println("Dealer hits");
            dealerHand.addCard(deck.drawCard());
            System.out.println("The Dealer's hand is: " + dealerHand);
            if (dealerHand.isBust()) {
                System.out.println("Dealer busts! You win!");
                return true;
            }
        }

        if (playerHand.getScore() < dealerHand.getScore()) {
            System.out.println("Your hand is worse than the dealer's. You lose.");
            return false;
        } else if (playerHand.getScore() == dealerHand.getScore()) {
            System.out.println("Push! Your hands are equals");
            return false; // we'll revisit this in part 2
        } else {
            System.out.println("You win! Congratulations!");
            return true;
        }
    }

    public boolean dealerHit(Hand dealerHand) {
        if (dealerHand.getSoftScore() <= 17) {
            return true;
        } else if (dealerHand.getScore() <= 16) {
            return true;
        } else {
            return false;
        }
    }
    
    public  boolean hitOrStay(String prompt) {
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
