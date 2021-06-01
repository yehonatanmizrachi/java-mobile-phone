package blackjack.backend;

public class Dealer extends Player {
	
	public Dealer()
	{
		super();
		
	}
	@Override
	public void makeMove(DeckOfCards DC) {
		
		if(this.sumOfCards < 17) {
			Card c = DC.getCard();
			this.addCard(c);
		}
		
	}

}
