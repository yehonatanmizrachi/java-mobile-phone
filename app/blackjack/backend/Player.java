package blackjack.backend;

import java.util.ArrayList;

public abstract class Player{
	private ArrayList<Card> my_cards = new ArrayList<Card>();
	int sumOfCards;
	public abstract void makeMove(DeckOfCards DC);
	
	public Player()
	{
		this.sumOfCards = 0;
	}
	
	public void addCard(Card c)
	{
		my_cards.add(c);
		
		this.sumOfCards = this.sumCards();
	}
	
	public ArrayList<Card> getMyCards()
	{
		return this.my_cards;
	}

	public void cleanCards() {
		my_cards.clear();
	}
	
	// AS = -1;
	public int sumCards()
	{
		int numOfAS = 0;
		int sum = 0;
		int i;
		
		for(i = 0; i < my_cards.size();i++) {
			
			int number = my_cards.get(i).Number();
			
			if(number == -1) {
				numOfAS++;
			}
			else {
				if(number > 10) {
					sum = sum + 10;
				}
				else {
					sum = sum + number;
				}
			}
		}
		
		if(sum >= 21) {
			sum = (sum + (numOfAS)*1);
		}
		
		else {
			
			for(i = 0; i < numOfAS ;i++) {
														
				if(sum + 11 <= 21) {
					
					sum = sum + 11;
					
				}
				else {
					sum = sum + 1;
				}
				
			}
		}

		return sum;
		
	}
}
