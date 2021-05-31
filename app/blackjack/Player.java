package blackjack;

import java.util.ArrayList;

public abstract class Player {
	private ArrayList<Card> my_cards;
	
	public abstract void makeMove();
	public void addCard(Card c)
	{
		my_cards.add(c);
	}
	public ArrayList<Card> getMyCards()
	{
		return this.my_cards;
	}
}
