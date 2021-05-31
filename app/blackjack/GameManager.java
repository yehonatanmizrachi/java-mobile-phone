package blackjack;

import java.util.ArrayList;
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
	
	public JSONObject sendCommand(JSONObject cmd)
	{
		switch ((COMMAND)cmd.get("command"))
		{
			case COMMAND.Start_Game:
				break;
		}
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
		if (players[0].sumOfCards > 21)
			return 1;
		else if (players[1].sumOfCards > 21)
			return 0;
		else
			return -1;
	}
	
	private void saveGame()
	{
		
	}
}
