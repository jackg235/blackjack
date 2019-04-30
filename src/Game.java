import java.util.Scanner;

import Strategy.BasicStrategy;
import cardObjects.*;

public class Game {

    private Scanner sc;
    private int numDecks;
    private BasicStrategy strat;
    private String[][] choices;
    private int numGames;
    private int totalWinnings;
    private int totalWager;
    // private int money = 100;

    public Game(int decks, Scanner sc) {
        this.numDecks = decks;
        this.sc = sc;
        strat = new BasicStrategy(numDecks, 17);
        choices = strat.getStrategyTable();
        numGames = 0;
        totalWinnings = 0;
        totalWager = 0;
    }

    public int getTotalWager() {
        return this.totalWager;
    }

    public int deal(Hand playerHand, Hand dealerHand, Deck deck, boolean sameValues) {
        System.out.println();
        System.out.println(playerHand.toString() + " player hand");
        System.out.println(dealerHand.toString() + " dealer hand");
        int winnings = 0;
        boolean split = false;
        Hand playerHand2 = new Hand();
        int bet = deck.getOptimalBet(10);
        totalWager += bet;
        if (playerHand.getScore() == 21) {
            winnings += 1.5 * bet;
            System.out.println(" blackjack ");
            return winnings;
        } else {
            int playerScore = playerHand.getHardScore();
            boolean isHardScore = true;

            int dealerShowingScore = dealerHand.getDealerShowing().getBlackjackScore();

            if (playerHand.getSoftScore() != playerHand.getHardScore()) {
                playerScore = playerHand.getSoftScore();
                isHardScore = false;
            }
            
            String move = getRecommendation(playerScore, dealerShowingScore, isHardScore, sameValues);

            while (true) {

                if (move.equals("H")) {
                    System.out.println("player hit");
                    playerHand.addCard(deck.drawCard());
                    if (playerHand.isBust()) {
                        System.out.println("player busts");
                        return winnings -= bet;
                    }
                    else if (playerHand.getNumCards() >= 5) {
                        break;
                    }
                } else if (move.equals("P")) {
                    System.out.println("player split");
                    if (deck.getNumCardsRemaining() >= 2) {
                        int value = playerHand.getHardScore() / 2;
                        playerHand = new Hand();
                        Card c = new Card(Integer.toString(value), "Hearts");
                        Card c2 = new Card(Integer.toString(value), "Hearts");
                        playerHand.addCard(c);
                        playerHand2.addCard(c2);
                        playerHand.addCard(deck.drawCard());
                        playerHand2.addCard(deck.drawCard());
                        split = true;
                        break;
                    } 
                } else if (move.equals("S")) {
                    System.out.println("player stay");
                    break;
                } else if (move.equals("DH") || move.equals("DS")) {
                    System.out.println("player double");
                    bet *= 2;

                    playerHand.addCard(deck.drawCard());
                    if (playerHand.isBust()) {
                        System.out.println("player bust");
                        return winnings -= bet;
                    }
                    else if (playerHand.getNumCards() >= 5) {
                        break;
                    } 
                    break;

                } 

                playerScore = playerHand.getHardScore();
                isHardScore = true;

                dealerShowingScore = dealerHand.getDealerShowing().getBlackjackScore();

                if (playerHand.getSoftScore() != playerHand.getHardScore()) {
                    playerScore = playerHand.getSoftScore();
                    isHardScore = false;
                }

                move = getRecommendation(playerScore, dealerShowingScore, isHardScore, false);
                System.out.println(playerHand.toString() + " player hand");
                
            }
            if (split) {
                bet *= 2;
                if (playerHand.getScore() < dealerHand.getScore() && playerHand2.getScore() < dealerHand.getScore()) {
                    System.out.println("Split and lost");
                    return winnings -= bet;
                }

                while (dealerHit(dealerHand)) {
                    // System.out.println("Dealer hits");
                    dealerHand.addCard(deck.drawCard());
                    // System.out.println("The Dealer's hand is: " + dealerHand);
                    if (dealerHand.isBust()) {
                        System.out.println("player won");
                        return winnings += bet;
                    }
                }
                if (playerHand.getScore() < dealerHand.getScore()) {
                    winnings -= bet / 2;
                }
                if (playerHand2.getScore() < dealerHand.getScore()) {
                    winnings -= bet / 2;
                }
                if (playerHand.getScore() > dealerHand.getScore()) {
                    winnings += bet / 2;
                }
                if (playerHand2.getScore() > dealerHand.getScore()) {
                    winnings += bet / 2;
                }
                System.out.println(winnings + " player split winnings");
                return winnings;

            }

            if (playerHand.getScore() < dealerHand.getScore()) {
                System.out.println(" player lost ");
                return winnings -= bet;
            }

            while (dealerHit(dealerHand)) {
                System.out.println("Dealer hits");
                dealerHand.addCard(deck.drawCard());
                System.out.println("The Dealer's hand is: " + dealerHand);
                if (dealerHand.isBust()) {
                    System.out.println("Dealer busts! You win!");
                    return winnings += bet;
                }
            }

            if (playerHand.getScore() < dealerHand.getScore()) {
                System.out.println("Your hand is worse than the dealer's. You lose.");
                return winnings -= bet;
            } else if (playerHand.getScore() == dealerHand.getScore()) {
                System.out.println("Push! Your hands are equals");
                return 0; // push
            } else {
                System.out.println("You win! Congratulations!");
                return winnings += bet;
            }
        }
    }

    public int totalNumberOfGames() {
        return numGames;
    }

    public int getWinnings() {
        return totalWinnings;
    }

    public int playRound() {
        Deck deck = new Deck(numDecks);
        deck.shuffle();
        boolean play = true;
        int numWins = 0;
        totalWinnings = 0;
        while (play) {
            try {
                numGames++;
                Hand playerHand = new Hand();
                Hand dealerHand = new Hand();
                Card c = deck.drawCard();
                Card c2 = deck.drawCard();
                boolean sameValues = false;
                if (c.getBlackjackScore() == c2.getBlackjackScore()) {
                    sameValues = true;
                }
                playerHand.addCard(c);
                playerHand.addCard(c2);
                dealerHand.addCard(deck.drawCard());
                dealerHand.addCard(deck.drawCard());
                int winnings = deal(playerHand, dealerHand, deck, sameValues);
                totalWinnings += winnings;
                if (winnings > 0) {
                    numWins++;
                }
                if (deck.getNumCardsRemaining() <= 10) {
                    play = false;
                }

            } catch (Exception e) {
                System.out.println("Deck is empty");
                play = false;
            }
        }
        return numWins;
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

    public String getRecommendation(int playerScore, int dealerShowingScore, boolean isHardScore, boolean sameValues) {
        
        int row = 0;
        
        if (sameValues) {
            row = (playerScore / 2) + 24;
            if (playerScore / 2 == 0) {
                row = 35;
            }
        } else if (!isHardScore) {
            row = playerScore - 5;
        } else {
            row = playerScore - 4;
        }

        int col = 0;

        if (dealerShowingScore == 1) {
            col = 9;
        } else {
            col = dealerShowingScore - 2;
        }

        if (row < 0 || col < 0) {
            System.out.println(playerScore + " " + dealerShowingScore + " " + isHardScore);
        }

        return choices[row][col];
    }

}
