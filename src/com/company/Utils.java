package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Utils {
    public static final String[] SUIT = {"Spades", "Hearts", "Diamonds", "Clubs"};
    public static final String[] RANK = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    public static List<Card> deck = new ArrayList<>();
    public static boolean matchBySuit = false;
    public static boolean matchByRank = false;
    public static int decks = 1;
    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();


    public static void getInput() {
        System.out.println("How many decks would you like to play with?");
        decks = sc.nextInt();
        System.out.println("Do you want to match by suit, rank or both?");
        String matchBy = sc.next();
        if (matchBy.equalsIgnoreCase("suit")) {
            matchBySuit = true;
        } else if (matchBy.equalsIgnoreCase("rank")) {
            matchByRank = true;
        } else if (matchBy.equalsIgnoreCase("both")) {
            matchBySuit = true;
            matchByRank = true;
        }
    }

    public static void createDeck() {
        for (int i = 0; i < decks; i++) {
            for (String suit : SUIT) {
                for (String rank : RANK) {
                    deck.add(new Card(rank, suit));
                }
            }
        }
    }

    public static void shuffleDeck() {
        deck.sort((card1, card2) -> random.nextInt(3) - 1);
    }

    public static void startGame() {
        List<Card> player1 = new ArrayList<>();
        List<Card> player2 = new ArrayList<>();
        for (int i = 0; i < deck.size(); i++) {
            if (i % 2 == 0) {
                player1.add(deck.get(i));
            } else {
                player2.add(deck.get(i));
            }
        }
        int player1Score = 0;
        int player2Score = 0;
        while (!deck.isEmpty()) {
            Card card1 = player1.remove(0);
            Card card2 = player2.remove(0);
            System.out.println("Player 1 plays " + card1 + " and Player 2 plays " + card2);
            if (matchBySuit && matchByRank && card1.getSuit().equals(card2.getSuit()) && card1.getRank().equals(card2.getRank())) {
                System.out.println("SNAP!");
                player1Score++;
                player2Score++;
                List<Card> snapPile = new ArrayList<>();
                snapPile.addAll(player1);
                snapPile.addAll(player2);
                if (random.nextBoolean()) {
                    player1.addAll(snapPile);
                    System.out.println("Player 1 wins the snap pile.");
                } else {
                    player2.addAll(snapPile);
                    System.out.println("Player 2 wins the snap pile.");
                }
            } else if (matchBySuit && card1.getSuit().equals(card2.getSuit())) {
                System.out.println("SNAP!");
                if (random.nextBoolean()) {
                    player1.add(card1);
                    player1.add(card2);
                    System.out.println("Player 1 wins the snap pile.");
                } else {
                    player2.add(card1);
                    player2.add(card2);
                    System.out.println("Player 2 wins the snap pile.");
                }
            } else if (matchByRank && card1.getRank().equals(card2.getRank())) {
                System.out.println("SNAP!");
                if (random.nextBoolean()) {
                    player1.add(card1);
                    player1.add(card2);
                    System.out.println("Player 1 wins the snap pile.");
                } else {
                    player2.add(card1);
                    player2.add(card2);
                    System.out.println("Player 2 wins the snap pile.");
                }
            } else {
                player1.add(card1);
                player2.add(card2);
            }
        }
        System.out.println("The game has ended.");
        if (player1Score > player2Score) {
            System.out.println("Player 1 wins with a score of " + player1Score + " to " + player2Score + ".");
        } else if (player2Score > player1Score) {
            System.out.println("Player 2 wins with a score of " + player2Score + " to " + player1Score + ".");
        } else {
            System.out.println("The game is a draw with a score of " + player1Score + " to " + player2Score + ".");
        }
    }
}
