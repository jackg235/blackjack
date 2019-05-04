import java.util.Scanner;

import Strategy.BasicStrategy;
import cardObjects.*;

public class Game {

    private Scanner sc;
    private int numDecks;
    private BasicStrategy strat;
    private String[][] choices;
    private int totalWager;
    private boolean splitWonLoss = false;

    public Game(int decks, Scanner sc) {
        this.numDecks = decks;
        this.sc = sc;
        strat = new BasicStrategy(numDecks, 17);
        choices = strat.getStrategyTable();
        totalWager = 0;
        splitWonLoss = false;
    }

    public int getTotalWager() {
        return this.totalWager;
    }
    
    public boolean splitWonLost() {
        return splitWonLoss;
    }

    public boolean playRound(Deck deck) {
        int bet = deck.getOptimalBet(10);
        totalWager += bet;

        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        Hand playerHand2 = new Hand();
        boolean split = false;
        splitWonLoss = false;

        try {
            playerHand.addCard(deck.drawCard());
            dealerHand.addCard(deck.drawCard());
            playerHand.addCard(deck.drawCard());
            dealerHand.addCard(deck.drawCard());
        } catch (Exception e) {
            Play.count--;
            return false;
        }

        if (playerHand.getScore() == 21) {
            System.out.println("Blackjack! You win!");
            Play.net += 1.5 * bet;
            return true;
        }

        System.out.println("Your hand:" + playerHand);
        System.out.println("The dealer is showing: " + dealerHand.getDealerShowing());

        int playerScore = playerHand.getHardScore();
        boolean isHardScore = true;

        int dealerShowingScore = dealerHand.getDealerShowing().getBlackjackScore();

        if (playerHand.getSoftScore() != playerHand.getHardScore()) {
            playerScore = playerHand.getSoftScore();
            isHardScore = false;
        }

        String move = getRecommendation(playerScore, dealerShowingScore, isHardScore, playerHand.isPair());

        if (move.equals("H") || move.equals("DH")) {
            System.out.println("We recommend you hit!");
        } else if (move.equals("S") || move.equals("DS")) {
            System.out.println("We recommend you stay!");
        }

        while (continuePlaying("Press 'y' to continue.")) {

            if (move.equals("H")) {

                playerHand.addCard(deck.drawCard());

                if (playerHand.isBust()) {
                    System.out.println("Your hand is: " + playerHand);
                    System.out.println("You busted! You lose!");
                    Play.net -= bet;
                    return false;
                }
                System.out.println("Your hand is: " + playerHand);
                if (playerHand.getNumCards() >= 5) {
                    System.out.println("You have 5 cards and cannot hit again.");
                    break;
                }
                // handle splitting
            } else if (move.equals("P")) {
                bet *= 2;
                System.out.println("player split");
                
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
            } else if (move.equals("S")) {
                break;
            } else if (move.equals("DH")) {

                bet *= 2;

                playerHand.addCard(deck.drawCard());
                if (playerHand.isBust()) {
                    System.out.println("Your hand is: " + playerHand);
                    System.out.println("You busted! You lose!");
                    Play.net -= bet;
                    return false;
                }
                System.out.println("Your hand is: " + playerHand);
                if (playerHand.getNumCards() >= 5) {
                    System.out.println("You have 5 cards and cannot hit again.");
                    break;
                }

            } else if (move.equals("DS")) { // if the strategy says double stand
                bet *= 2;
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

            if (move.equals("H") || move.equals("DH")) {
                System.out.println("We recommend you hit!");
            } else if (move.equals("S") || move.equals("DS")) {
                System.out.println("We recommend you stay!");
            }
        }

        while (dealerHit(dealerHand)) {
            System.out.println("Dealer hits");
            dealerHand.addCard(deck.drawCard());
            System.out.println("The Dealer's hand is: " + dealerHand);
            if (dealerHand.isBust()) {
                System.out.println("Dealer busts! You win!");
                Play.net += bet;
                return true;
            }
        }

        if (split) {
            if (playerHand.getScore() < dealerHand.getScore() && playerHand2.getScore() < dealerHand.getScore()) {
                System.out.println("Split and lost");
                Play.net -= bet;
                return false;
            }
            if (playerHand.getScore() > dealerHand.getScore() && playerHand2.getScore() > dealerHand.getScore()) {
                System.out.println("Split and won both kands");
                Play.net += bet;
                return true;
            }
            if (playerHand.getScore() < dealerHand.getScore()) {
                System.out.println("Split and lost a hand");
                Play.net -= (bet / 2);
            }
            if (playerHand.getScore() > dealerHand.getScore()) {
                System.out.println("Split and won a hand");
                Play.net += (bet / 2);
            }
            if (playerHand2.getScore() < dealerHand.getScore()) {
                System.out.println("Split and lost a hand");
                Play.net -= (bet / 2);
            }
            if (playerHand2.getScore() > dealerHand.getScore()) {
                System.out.println("Split and won a hand");
                Play.net += (bet / 2);
            }
            Play.numTies++;
            splitWonLoss = true;
            return false;
        }

        if (playerHand.getScore() < dealerHand.getScore()) {
            System.out.println("Your hand is worse than the dealer's. You lose.");
            Play.net -= bet;
            return false;
        } else if (playerHand.getScore() == dealerHand.getScore()) {
            Play.numTies++;
            System.out.println("Push! Your hands are equals");
            return false; 
        } else {
            System.out.println("You win! Congratulations!");
            Play.net += bet;
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

    public boolean continuePlaying(String prompt) {
        return true;
    }

    public String getRecommendation(int playerScore, int dealerShowingScore, boolean isHardScore, boolean shouldSplit) {
        
        int row = 0;
        int col = 0;
        
        if (shouldSplit) {
            if (!isHardScore) {
                row = 35;
            } else {
                row = (playerScore / 2) + 24;
            }
        } else {
            if (!isHardScore) {
                row = playerScore - 5;
            } else {
                row = playerScore - 4;
            }
        }
        
        if (dealerShowingScore == 1) {
            col = 9;
        } else {
            col = dealerShowingScore - 2;
        }

        return choices[row][col];
    }

}