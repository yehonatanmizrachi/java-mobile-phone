package blackjack;

import java.util.ArrayList;
import org.apiguardian.*;
import org.json.JSONObject;


public class GameManager {
	
	private final int numOfPlayers = 2;
	private Player[] players = new Player[numOfPlayers];
	private int turn; // 0- computer, 1- player1...
	private DeckOfCards cards_deck;
	
	public GameManager()
	{
		this.turn = 1;
	}
	
	public JSONObject sendCommand()
	{
		
	}
	
	public ArrayList<ArrayList<Card>> startGame()
	{
		ArrayList<ArrayList<Card>> temp = null;
		
		for (int i = 0; i < numOfPlayers; i++)
		{
			players[i].addCard(cards_deck.getCard());
			players[i].addCard(cards_deck.getCard());
			temp.add(players[i].getMyCards());
		}
		return temp;
	}
	
	private int checkGameStatus()
	{
		return 0;
	}
	
	private void saveGame()
	{
		
	}
}
