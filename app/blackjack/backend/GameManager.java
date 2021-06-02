package blackjack.backend;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import blackjack.api.COMMAND;
import blackjack.api.GAME_STATUS;

public class GameManager{
	
	private final int numOfPlayers = 2;
	private Player[] players = new Player[numOfPlayers];
	private GAME_STATUS status;
	private DeckOfCards cards_deck = new DeckOfCards();
	
	public GameManager()
	{
		players[0] = new Dealer();
		players[1] = new User(1);
		this.status = GAME_STATUS.START;
	}
	
	public JSONObject sendCommand(JSONObject cmd) throws JSONException
	{
		checkGameStatus();
		COMMAND command = (COMMAND)cmd.get("command");
		if (command == COMMAND.START_GAME)
		{
			players[0].cleanCards();
			players[1].cleanCards();
			((User)players[1]).incTotalGames();
			startGame();
			this.status = GAME_STATUS.RUNNING;
		}
		else if (command == COMMAND.HIT)
			players[1].makeMove(cards_deck);
		else if (command == COMMAND.STAND)
		{
			int old_sum = players[0].sumOfCards;
			players[0].makeMove(cards_deck);
			this.status = GAME_STATUS.DEALER_TURN;
			if (old_sum == players[0].sumOfCards)
			{
				this.status = GAME_STATUS.END_GAME;
				checkWhoWon();
			}
		}
		else if (command == COMMAND.EXIT)
			saveGame();
		
		checkGameStatus();
		return buildJson();
	}
	
	private void checkWhoWon()
	{
		if (21 - players[0].sumOfCards < 21 - players[1].sumOfCards)
			this.status = GAME_STATUS.DEALER_WINS;
		else
			this.status = GAME_STATUS.PLAYER_WINS;
	}
	
	public JSONObject buildJson() throws JSONException
	{
		JSONObject obj = new JSONObject();
		JSONObject p = new JSONObject();
		JSONObject d = new JSONObject();

		d.put("cards", this.players[0].getMyCards());
		obj.put("Dealer", d);
		
		p.put("cards", this.players[1].getMyCards());
		p.put("money", ((User)players[1]).getMoney());
		p.put("wins", ((User)players[1]).getWins());
		p.put("totalGames", ((User)players[1]).getTotalGames());
		obj.put("player", p);
		
		obj.put("status", this.status);
		return obj;
	}
	
	public void startGame()
	{	
		players[0].sumOfCards = 0;
		players[1].sumOfCards = 0;
		cards_deck.reset();
		for (int i = 0; i < numOfPlayers; i++)
		{
			players[i].addCard(cards_deck.getCard());
			players[i].addCard(cards_deck.getCard());
		}	
	}
	
	private void checkGameStatus()
	{
		if (players[0].sumOfCards > 21 || players[1].sumOfCards == 21)
			this.status = GAME_STATUS.PLAYER_WINS;
		else if (players[1].sumOfCards > 21)
			this.status = GAME_STATUS.DEALER_WINS;
	}
	
	private void saveGame()
	{
		try {
		      FileWriter myWriter = new FileWriter("./app/blackjack/players/" + "player" + 1 + ".txt");
		      myWriter.write(((User)players[1]).getMoney() + "\n" + ((User)players[1]).getWins() + "\n" + ((User)players[1]).getTotalGames());
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
}
