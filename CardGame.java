package cardGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.math; //added math

//card class by gabriel bostic and Uriel Ramos
public class Card {
    private String suit;
    private String name;
    private String picture;
    private int value;
	
	public Card(String cardSuit, String cardName, int cardValue, String cardPicture) {
	suit = cardSuit;
 	name = cardName;
	value = cardValue;
	picture = cardPicture;
	
	}
			// accessors
		public String getSuit() {
		    return suit;
		}
		public String getName() {
		    return name;
		}
		public int getValue() {
		    return value;
		}
		public String getPicture() {
		    return picture;
		}

	// toString method - represents values as a string - By uriel
public String toString() {
    return name + " of " + suit + " (Value: " + value + ", Picture: " + picture + ")";
}
	
}


public class CardGame {

	private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
	private static ArrayList<Card> playerCards = new ArrayList<Card>();


	public static void main(String[] args) {

		Scanner input = null;
		try {
			input = new Scanner(new File("cards.txt"));
		} catch (FileNotFoundException e) {
			// error
			System.out.println("error");
			e.printStackTrace();
		}

		while(input.hasNext()) {
			String[] fields  = input.nextLine().split(",");
			//	public Card(String cardSuit, String cardName, int cardValue, String cardPicture) {
			Card newCard = new Card(fields[0], fields[1].trim(),
					Integer.parseInt(fields[2].trim()), fields[3]);
			deckOfCards.add(newCard);	
		}

		shuffle();

		//for(Card c: deckOfCards)
			//System.out.println(c);

		//deal the player 5 cards
		for(int i = 0; i < 4; i++) {
			playerCards.add(deckOfCards.remove(i));
		}
		
		System.out.println("players cards");
		for(Card c: playerCards)
			System.out.println(c);

		System.out.println("pairs is " + checkFor2Kind());

	}//end main

	public static void shuffle() {

		//shuffling the cards by deleting and reinserting
		for (int i = 0; i < deckOfCards.size(); i++) {
			int index = (int) (Math.random()*deckOfCards.size());
			Card c = deckOfCards.remove(index);
			//System.out.println("c is " + c + ", index is " + index);
			deckOfCards.add(c);
		}
	}

	//check for 2 of a kind in the players hand
	public static boolean checkFor2Kind() {

		int count = 0;
		for(int i = 0; i < playerCards.size() - 1; i++) {
			Card current = playerCards.get(i);
			Card next = playerCards.get(i+1);
			
			for(int j = i+1; j < playerCards.size(); j++) {
				next = playerCards.get(j);
				//System.out.println(" comparing " + current);
				//System.out.println(" to " + next);
				if(current.equals(next))
					count++;
			}//end of inner for
			if(count == 1)
				return true;

		}//end outer for
		return false;
	}
}//end class
