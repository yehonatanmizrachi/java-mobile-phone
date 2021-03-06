package blackjack.backend;


import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

import blackjack.api.CARD_SHAPE;
import blackjack.api.COMMAND;
import blackjack.api.GAME_STATUS;

public class GameManager{
	
	private final int numOfPlayers = 2;
	private Player[] players = new Player[numOfPlayers];
	private GAME_STATUS status;
	private DeckOfCards cards_deck = new DeckOfCards();
	private final int betVal = 10;
	
	public GameManager()
	{
		players[0] = new Dealer();
		players[1] = new User(1);
		this.status = GAME_STATUS.START;
	}
	
	public JSONObject sendCommand(JSONObject cmd) throws JSONException
	{
		int i = 0;
		COMMAND command = (COMMAND)cmd.get("command");
		if (command == COMMAND.START_GAME)
		{
			players[0].cleanCards();
			players[1].cleanCards();
			((User)players[1]).incTotalGames();
			this.status = GAME_STATUS.RUNNING;
			startGame();
		}
		else if (command == COMMAND.HIT)
			players[1].makeMove(cards_deck, players[0].sumOfCards);
		else if (command == COMMAND.STAND)
		{
			if (players[0].getMyCards().get(0).Number() == 0)
				players[0].removeFirstCard();
			
			int old_sum = players[0].sumOfCards;
			players[0].makeMove(cards_deck, players[1].sumOfCards);
			this.status = GAME_STATUS.DEALER_TURN;
			if (old_sum == players[0].sumOfCards)
			{
				this.status = GAME_STATUS.END_GAME;
				i = 1;
				checkGameStatus(i);
			}
		}
		else if (command == COMMAND.EXIT)
			saveGame();
		else if (command == COMMAND.STATS) {
			return getStatistics();
		}
		if(i == 0) {
			checkGameStatus(i);
		}
		return buildJson();
	}
	
	
	public JSONObject buildJson() throws JSONException
	{
		JSONObject obj = new JSONObject();
		JSONObject p = new JSONObject();
		JSONObject d = new JSONObject();

		d.put("cards", this.players[0].getMyCards());
		d.put("sumOfCards", this.players[0].sumOfCards);
		obj.put("dealer", d);
		
		p.put("cards", this.players[1].getMyCards());
		p.put("sumOfCards", this.players[1].sumOfCards);
		obj.put("player", p);
		
		obj.put("status", this.status);
		return obj;
	}
	
	public void startGame()
	{	
		
		if (((User)players[1]).getMoney() - betVal < 0)
		{
			((User)players[1]).setMoney(((User)players[1]).getMoney() + betVal);
		}
		
		((User)players[1]).setMoney(((User)players[1]).getMoney() - betVal);
		players[0].sumOfCards = 0;
		players[1].sumOfCards = 0;
		cards_deck.reset();
		for (int i = 0; i < numOfPlayers; i++)
		{
			if(i == 0) {
				players[i].addCard(cards_deck.getBOC());
				players[i].makeMove(cards_deck, 0);
			}
			else {
				players[i].makeMove(cards_deck, 0);
				players[i].makeMove(cards_deck, 0);
			}
		}	
		
		
	}
	

	private void checkGameStatus(int i){
		
		if(i == 0){ // during game
			if((players[1].sumOfCards == 21 || players[0].sumOfCards > 21) && this.status != GAME_STATUS.PLAYER_WINS){
				System.out.println("A");
				this.status = GAME_STATUS.PLAYER_WINS;
				((User)players[1]).setMoney(((User)players[1]).getMoney() + betVal * 2);
				((User)players[1]).setWins(1);
			}
			else{
				if(players[1].sumOfCards > 21 && this.status != GAME_STATUS.DEALER_WINS){
					this.status = GAME_STATUS.DEALER_WINS;
				}
			}
		}
		
		else{ // if GAME_STATUS == STANDS
			
			if (21 - players[0].sumOfCards < 21 - players[1].sumOfCards && this.status != GAME_STATUS.DEALER_WINS)
				this.status = GAME_STATUS.DEALER_WINS;
			else{
				if(players[0].sumOfCards == players[1].sumOfCards && this.status != GAME_STATUS.TIE_GAME){
					this.status = GAME_STATUS.TIE_GAME;
					((User)players[1]).setMoney(((User)players[1]).getMoney() + betVal);
				}
				else{
					if (this.status != GAME_STATUS.PLAYER_WINS) {
						this.status = GAME_STATUS.PLAYER_WINS;
						((User)players[1]).setMoney(((User)players[1]).getMoney() + betVal * 2);
						((User)players[1]).setWins(1);
					}
				}
			}
		}
	}
	
	private void saveGame()
	{
		User u = new User(1);
		try {
		      FileWriter myWriter = new FileWriter("./app/blackjack/players/" + ".player" + 1);
		      myWriter.write(((User)players[1]).getMoney() + "\n" + ((User)players[1]).getWins() + "\n" + ((User)players[1]).getTotalGames());
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	
	public static JSONObject getStatistics() throws JSONException{
		JSONObject obj = new JSONObject();
		User u = new User(1);
		obj.put("money", u.getMoney());
		obj.put("wins", u.getWins());
		obj.put("totalGames", u.getTotalGames());
		obj.put("status", GAME_STATUS.STATS);
		return obj;
	}
}
