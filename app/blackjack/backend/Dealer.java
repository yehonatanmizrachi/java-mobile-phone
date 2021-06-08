package blackjack.backend;

public class Dealer extends Player {
	
	public Dealer()
	{
		super();
		
	}
	@Override
	public void makeMove(DeckOfCards DC, int evSum) {
		
		if (21 - evSum > 21 - this.sumOfCards)
			return;
		
		if (this.sumOfCards < 17 || (21 - evSum < 21 - this.sumOfCards)) {
			Card c = DC.getCard();
			this.addCard(c);
			this.sumCards();
		}
	}

}
