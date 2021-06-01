package blackjack.backend;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import blackjack.api.COMMAND;
import blackjack.api.GAME_STATUS;

public class GameManager {
	
	private final int numOfPlayers = 2;
	private Player[] players = new Player[numOfPlayers];
	private DeckOfCards cards_deck;
	private GAME_STATUS status;
	
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
			startGame();
			this.status = GAME_STATUS.RUNNING;
		}
		else if (command == COMMAND.HIT)
			players[1].addCard(this.cards_deck.getCard());
		else if (command == COMMAND.STOP)
		{
			int old_sum = players[0].sumOfCards;
			players[0].makeMove();
			this.status = GAME_STATUS.DEALER_TURN;
			if (old_sum == players[0].sumOfCards)
				this.status = GAME_STATUS.END_GAME;

		}
		else if (command == COMMAND.EXIT)
			saveGame();
		return buildJson();
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
		obj.put("player", p);
		
		obj.put("status", this.status);
		return obj;
	}
	
	public void startGame()
	{	
		cards_deck.reset();
		for (int i = 0; i < numOfPlayers; i++)
		{
			players[i].addCard(cards_deck.getCard());
			players[i].addCard(cards_deck.getCard());
		}	
		cards_deck.shuffle();
	}
	
	private void checkGameStatus()
	{
		if (players[0].sumOfCards > 21)
			this.status = GAME_STATUS.PLAYER_WINS;
		else if (players[1].sumOfCards > 21)
			this.status = GAME_STATUS.DEALER_WINS;
	}
	
	private void saveGame()
	{
		try {
		      FileWriter myWriter = new FileWriter("./app/blackjack/players/" + "player" + 1 + ".txt");
		      myWriter.write(((User)players[1]).getMoney() + "\n" + ((User)players[1]).getWins());
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
}