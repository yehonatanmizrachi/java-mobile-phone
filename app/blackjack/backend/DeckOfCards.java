package blackjack.backend;

import java.util.ArrayList;
import java.util.Random;


public class DeckOfCards {

	private ArrayList<Card> DC = new ArrayList<Card>();
	private int amount;
	
	private void swapCards(int i,int j) {
		Card temp = DC.get(i);
		DC.set(i, DC.get(j));
		DC.set(j, temp);
	}
	
	public DeckOfCards() {
		this.reset();
	}
	
	// get card from the deck
	public Card getCard() {
		if(amount == 0) {
			// sign that the deck is empty
			return null;
		}
		Card pick = DC.get(0);
		DC.remove(0);
		--amount;
		return pick;
	}
	private String get_pic(int j,int num) {
		String id = "";
		
		if(num>=2 && num<=10) {
			id = id + Integer.toString(num);
		}
		if(num == 1) {
			id = id + "A";
		}
		if(num == 11) {
			id = id + "J";
		}
		if(num == 12) {
			id = id + "Q";
		}
		if(num == 13) {
			id = id + "K";
		}
		if(j == 0) {
			id = id + "D";
		}
		if(j == 1) {
			id = id + "C";
		}
		if(j == 2) {
			id = id + "H";
		}
		if(j == 3) {
			id = id + "S";
		}
		return id;
	}
	// reset the deck of cards
	public void reset() {
		int i,j;
		int num;
		String pic = "";
		this.amount = 52;
		
		// 13 sets * 4 = 52 cards
		// shape 0 = Diamonds, shape 1 = Clubs, shape 2 = Hearts, shape 3 = Spades
		// Jack = 11, Queen = 12, King = 13
		
		
		if(DC.isEmpty() != true) {
			DC = new ArrayList<Card>();
		}
		
		for(j = 0; j<4;j++) {
			
			int shape = j;
			
			for(i=0;i<13;i++) {
				num = i+1;
				
				pic = "./Pic/cards/" + this.get_pic(j, num) + ".png";
				
				Card c = new Card(num,shape,pic);
				DC.add(c);
			}
		}
		
		this.shuffle();
	}
	
	// shuffle the deck
	public void shuffle() {
		// we make 3 times - swap 52 times random places in the deck
		Random rand = new Random();
		int i,j,k,times;
		
		for(times = 0; times<3;times++) {
			
			for(k = 0; k < DC.size(); k++) {
				i = rand.nextInt(DC.size());
				j = rand.nextInt(DC.size());
				this.swapCards(i, j);
			}
			
		}
	}
}
