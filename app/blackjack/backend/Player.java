package blackjack.backend;

import java.util.ArrayList;

public abstract class Player {
	private ArrayList<Card> my_cards;
	int sumOfCards;
	public abstract void makeMove();
	
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
}
