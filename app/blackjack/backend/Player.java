package blackjack.backend;

import java.util.ArrayList;

public abstract class Player{
	private ArrayList<Card> my_cards = new ArrayList<Card>();;
	int sumOfCards;
	public abstract void makeMove(DeckOfCards DC);
	
	public Player()
	{
		this.sumOfCards = 0;
	}
	
	public void addCard(Card c)
	{
		my_cards.add(c);
		this.sumOfCards += c.getNumber();
	}
	
	public ArrayList<Card> getMyCards()
	{
		return this.my_cards;
	}

	public void cleanCards() {
		my_cards.clear();
	}
	
	// AS = -1;
	public void sumCards()
	{
		
	}
}
