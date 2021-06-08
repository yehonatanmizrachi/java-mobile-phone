package blackjack.backend;

import java.util.ArrayList;

public abstract class Player{
	private ArrayList<Card> my_cards = new ArrayList<Card>();
	int sumOfCards;
	public abstract void makeMove(DeckOfCards DC, int evSum);
	
	public Player()
	{
		this.sumOfCards = 0;
	}
	public void removeFirstCard() {
		my_cards.remove(0);
	}	
	public void addCard(Card c)
	{
		my_cards.add(c);
	}
	
	public ArrayList<Card> getMyCards()
	{
		return this.my_cards;
	}

	public void cleanCards() {
		my_cards.clear();
	}
	
	// AS = 1;
	public void sumCards()
	{
		int numOfAS = 0;
		int sum = 0;
		int i;
		
		for(i = 0; i < my_cards.size();i++) {
			
			int number = my_cards.get(i).Number();

			if(number <= 10 && number >= 2) {
				sum = sum + number;
			}
			else if (number == 1) {
				numOfAS++;
			}
			else if (number != 0)
				sum = sum + 10;
		}
		
		if(sum >= 21) {
			sum = (sum + (numOfAS)*1);
		}
		
		else {
			
			for(i = 0; i < numOfAS ;i++) {
														
				if (numOfAS - i != 1)
					sum += 1;
				else if (sum + 11 <= 21)
					sum += 11;
				else
					sum += 1;

			}
		}		
		this.sumOfCards = sum;
	}
}
