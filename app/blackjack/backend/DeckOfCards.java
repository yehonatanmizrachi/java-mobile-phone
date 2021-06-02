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
	
	// reset the deck of cards
	public void reset() {
		int i,j;
		int num;
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
				Card c = new Card(num,shape);
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
